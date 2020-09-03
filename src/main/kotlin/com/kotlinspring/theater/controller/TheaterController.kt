package com.kotlinspring.theater.controller

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.kotlinspring.theater.model.Seat
import com.kotlinspring.theater.services.BookingService
import com.kotlinspring.theater.services.TheaterServices
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/theater")
class TheaterController {

    val mapper = jacksonObjectMapper()

    @Autowired
    lateinit var theaterServices: TheaterServices

    @Autowired
    lateinit var bookingService: BookingService

    @GetMapping("/greet")
    fun helloWorld(): String = "Hello World"

    @PostMapping("/isAvailable")
    fun checkAvailability(@RequestBody seat: Seat): JsonNode {
        val resNode = mapper.createObjectNode();
        val selectedSeat =  theaterServices.find(seat)

        resNode.set<JsonNode>("seat", mapper.valueToTree<JsonNode>(selectedSeat))
        resNode.put("isFree", bookingService.isSeatFree(selectedSeat))

        return resNode
    }

}
