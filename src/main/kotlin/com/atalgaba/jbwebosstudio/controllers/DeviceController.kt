package com.atalgaba.jbwebosstudio.controllers

import com.atalgaba.jbwebosstudio.enums.WebOSToolsCLIFile
import com.atalgaba.jbwebosstudio.models.Device
import com.atalgaba.jbwebosstudio.util.WebOSToolsCLIUtil
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

class DeviceController {
    private val json = Json { ignoreUnknownKeys = true; isLenient = true }

    fun getDevices(): List<Device> {
        val devicesFile = WebOSToolsCLIUtil.getFile(WebOSToolsCLIFile.NOVACOM_DEVICES)
        val devices: List<Device> = json.decodeFromString(ListSerializer(Device.serializer()), devicesFile.readText())
        return devices
    }
}