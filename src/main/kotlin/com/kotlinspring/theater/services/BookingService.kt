package com.kotlinspring.theater.services

import com.kotlinspring.theater.model.SeatExt
import org.springframework.stereotype.Service

@Service
class BookingService {

    fun isSeatFree(seat: SeatExt): Boolean = true

}