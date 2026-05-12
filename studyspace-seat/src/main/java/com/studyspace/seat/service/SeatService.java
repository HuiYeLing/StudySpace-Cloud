package com.studyspace.seat.service;

import com.studyspace.common.result.ApiResult;

public interface SeatService {
    ApiResult getSeatById(long id);
    ApiResult getListSeats();
    ApiResult updateSeatStatus(long id, String status);
    ApiResult reserveSeat(long seatId, String userId);
    ApiResult leaveSeat(long seatId, String userId);

}
