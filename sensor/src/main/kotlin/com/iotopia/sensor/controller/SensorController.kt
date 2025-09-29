package com.iotopia.sensor.controller

import com.iotopia.sensor.service.HumiditySensorService
import com.iotopia.sensor.service.IlluminationSensorService
import com.iotopia.sensor.service.TemperatureSensorService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SensorController(
    private val temperatureSensorService: TemperatureSensorService, // как происходит инжект
    private val humiditySensorService: HumiditySensorService,
    private val illuminationSensorService: IlluminationSensorService
) {
    @PostMapping("/api/v1/sensor/generate")
    fun generateData() {
        temperatureSensorService.generate()
        /*humiditySensorService.generate()
        illuminationSensorService.generate()*/
    }
}
