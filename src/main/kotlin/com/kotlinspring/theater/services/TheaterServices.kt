package com.kotlinspring.theater.services

import com.kotlinspring.theater.model.Seat
import com.kotlinspring.theater.model.SeatExt
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class TheaterServices {

    // SEAT PRICES:
    // Seats in rows 14 and 15 cost 14.50
    // Seats in rows 1 to 13 and numbered 1 to 3 or 34 to 36 cost 16.50
    // All other seats in row 1 cost 21.00
    // All other seats cost 18.00

    // SEAT DESCRIPTIONS:
    // Seats in row 15: "Back row"
    // Seats in row 14: "Cheaper seat"
    // All other rows, seats 1 to 3 and 34 to 36: "Restricted View"
    // All other seats in rows 1 and 2 "Best view"
    // All other seats: "Standard seat"
    private final val rows = 'A'..'O'
    final val seatsRow = 36
    val restricted1 = 1..3
    val restricted2 = 34..36
    val front = 'A'..'B'
    val seats
        get() = this.hiddenseats
    private val hiddenseats = mutableListOf<SeatExt>()
    init {
        val seatsCreation = arrayListOf<SeatExt>()
        var seat: SeatExt
        var price: BigDecimal
        var desc: String

        fun getPrice(row: Char, num: Int): BigDecimal {
            return when {
                row > 'M' -> BigDecimal(14.5)
                row in front -> BigDecimal(21)
                num in restricted1 || num in restricted2 -> BigDecimal(16.5)
                else -> BigDecimal(18)
            }
        }

        fun getDesc(row: Char, num: Int): String {
            return when {
                row == 'N' -> "cheap"
                row == 'O' -> "back row"
                row in front -> "Best"
                num in restricted1 || num in restricted2 -> "restricted"
                else -> "Standard"
            }
        }

        for (i in rows) {
            (1..seatsRow).forEach {
                hiddenseats.add(SeatExt(i, it, getPrice(i,it), getDesc(i,it)))
            }
        }
    }

    fun find(seat: Seat): SeatExt = seats.filter { it.row == seat.row && it.num == seat.num }.first()
}