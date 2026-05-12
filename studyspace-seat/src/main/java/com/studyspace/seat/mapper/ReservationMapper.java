package com.studyspace.seat.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ReservationMapper {

    @Insert("INSERT INTO reservation (seat_id, user_id, start_time, status) " +
            "VALUES (#{seatId}, #{userId}, NOW(), 'ACTIVE')")
    int insert(@Param("userId") Long userId, @Param("seatId") Long seatId);

    @Select("SELECT COUNT(*) FROM reservation " +
            "WHERE user_id = #{userId} AND seat_id = #{seatId} AND status = 'ACTIVE'")
    int countActiveByUserAndSeat(@Param("userId") Long userId,
                                 @Param("seatId") Long seatId);

    @Update("UPDATE reservation SET status = 'COMPLETED', end_time = NOW() " +
            "WHERE user_id = #{userId} AND seat_id = #{seatId} AND status = 'ACTIVE'")
    int completeByUserAndSeat(@Param("userId") Long userId,
                              @Param("seatId") Long seatId);
}