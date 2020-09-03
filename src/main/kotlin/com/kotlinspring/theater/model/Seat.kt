package com.kotlinspring.theater.model

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal


open class Seat(open val row: Char, open val num: Int)

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
data class SeatExt(@JsonProperty override val row: Char,
                   @JsonProperty override val num: Int,
                   @JsonProperty val price: BigDecimal,
                   @JsonProperty val description: String): Seat(row,num) {
    //YOU MAY NOT EDIT THIS CLASS!
    override fun toString(): String = "Seat $row-$num $$price ($description)"
}