package com.iotopia.sensor.service.impl

import com.iotopia.sensor.model.RoomType
import com.iotopia.sensor.sender.SensorSender
import com.iotopia.sensor.service.TemperatureSensorService
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class TemperatureSensorServiceImpl(
    private val sensorSender: SensorSender // как у нас реализован
) : TemperatureSensorService {

    override fun generate() {
        for (roomType in RoomType.entries) {
            repeat(5) {
                val roomId = roomType.hashCode()
                val temperature = Random.nextInt(20, 31)
                sensorSender.send(roomId.toString(), temperature.toString())
            }
        }
    }
}
