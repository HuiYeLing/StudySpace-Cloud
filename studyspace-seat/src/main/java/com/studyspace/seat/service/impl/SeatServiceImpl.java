package com.studyspace.seat.service.impl;

import com.studyspace.common.result.ApiResult;
import com.studyspace.seat.domain.Seat;
import com.studyspace.seat.feign.UserFeignClient;
import com.studyspace.seat.mapper.ReservationMapper;
import com.studyspace.seat.mapper.SeatMapper;
import com.studyspace.seat.service.SeatService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {
    @Autowired
    private SeatMapper seatMapper;
    @Autowired
    private ReservationMapper reservationMapper;
    @Autowired
    private UserFeignClient userFeignClient;

    // 通过id获取座位信息
    @Override
    public ApiResult getSeatById(long id) {
        Seat seat = seatMapper.getSeatById(id);
        if (seat != null) {
            return ApiResult.ok(seat);
        }
        return ApiResult.error("座位不存在");
    }

    // 获取所有座位信息
    @Override
    public ApiResult getListSeats() {
        List<Seat> seats = seatMapper.getListSeats();
        if (seats != null && !seats.isEmpty()) {
            return ApiResult.ok(seats);
        }
        return ApiResult.error("没有座位信息");
    }
    // 更新座位状态
    @Override
    public ApiResult updateSeatStatus(long id, String status) {
        int result = seatMapper.updateSeatStatus(id, status);
        if (result > 0) {
            return ApiResult.ok("更新座位状态成功");
        } else {
            return ApiResult.error("更新座位状态失败");
        }
    }

    // ========== 核心：预约座位 + 生成订单 ==========

    @Override
    @Transactional
    public ApiResult reserveSeat(long seatId, String userId) {
        // 1. 远程调用 user 服务验证用户身份
        ApiResult userResult = userFeignClient.getCurrentUser(Long.valueOf(userId));
        if (!userResult.isSuccess()) {
            return ApiResult.noauth("请先登录");
        }

        // 2. 查询座位
        Seat seat = seatMapper.getSeatById(seatId);
        if (seat == null) {
            return ApiResult.error("座位不存在");
        }
        if (!Seat.STATUS_AVAILABLE.equals(seat.getStatus())) {
            return ApiResult.error("座位不可用，当前状态：" + seat.getStatus());
        }

        // 3. 改座位状态为 OCCUPIED
        int updateResult = seatMapper.updateSeatStatus(seatId, Seat.STATUS_OCCUPIED);
        if (updateResult <= 0) {
            return ApiResult.error("预定座位失败，请稍后再试");
        }

        // 4. 写入预约订单（同一事务，回滚时座位状态也会回滚）
        reservationMapper.insert(Long.valueOf(userId), seatId);

        // 5. 返回最新座位信息
        seat = seatMapper.getSeatById(seatId);
        return ApiResult.ok(seat, "预定座位成功");
    }

    // ========== 离开座位 ==========

    @Override
    @Transactional
    public ApiResult leaveSeat(long seatId, String userId) {
        // 1. 验证用户
        ApiResult userResult = userFeignClient.getCurrentUser(Long.valueOf(userId));
        if (!userResult.isSuccess()) {
            return ApiResult.noauth("请先登录");
        }
        // 2. 查询座位
        Seat seat = seatMapper.getSeatById(seatId);
        if (seat == null) {
            return ApiResult.error("座位不存在");
        }
        if (!Seat.STATUS_OCCUPIED.equals(seat.getStatus())) {
            return ApiResult.error("座位未被占用，当前状态：" + seat.getStatus());
        }
        // 3. 验证是否是本人预约
        int count = reservationMapper.countActiveByUserAndSeat(
                Long.valueOf(userId), seatId);
        if (count == 0) {
            return ApiResult.error("您没有预约此座位");
        }
        // 4. 改座位状态 + 完成订单
        seatMapper.updateSeatStatus(seatId, Seat.STATUS_AVAILABLE);
        reservationMapper.completeByUserAndSeat(Long.valueOf(userId), seatId);
        // 5. 返回
        seat = seatMapper.getSeatById(seatId);
        return ApiResult.ok(seat, "已离开座位");
    }

}
