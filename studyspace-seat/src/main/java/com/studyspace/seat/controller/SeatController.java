package com.studyspace.seat.controller;

import com.studyspace.common.result.ApiResult;
import com.studyspace.seat.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/seat")
public class SeatController {
    @Autowired
    private SeatService seatService;
    // 通过id获取座位信息
    @GetMapping("/getSeatById")
    public ApiResult getSeatById(long id) {
        return seatService.getSeatById(id);
    }
    @GetMapping("/getListSeats")
    public ApiResult getListSeats() {
        return seatService.getListSeats();
    }
    @GetMapping("/updateSeatStatus")
    public ApiResult updateSeatStatus(long id, String status) {
        return seatService.updateSeatStatus(id, status);
    }
    @PostMapping("/reserveSeat")
    public ApiResult reserveSeat(@RequestParam long seatId,
                                 @RequestParam String userId) {  // ← 改成 RequestParam
        return seatService.reserveSeat(seatId, userId);
    }

    @PostMapping("/leaveSeat")
    public ApiResult leaveSeat(@RequestParam long seatId,
                               @RequestParam String userId) {
        return seatService.leaveSeat(seatId, userId);
    }
}
