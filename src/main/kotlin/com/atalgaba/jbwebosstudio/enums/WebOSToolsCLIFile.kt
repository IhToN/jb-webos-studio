package com.atalgaba.jbwebosstudio.enums

import com.atalgaba.jbwebosstudio.settings.AppSettingsState
import java.io.File

enum class WebOSToolsCLIFile(private val filename: String) {
    ARES("ares.json"),
    COMMAND_SERVICE("command-service.json"),
    CONFIG("config.json"),
    IPK("ipk.json"),
    NOVACOM_DEVICES("novacom-devices.json"),
    SDK("sdk.json"),
    TEMPLATE("template.json");

    fun getBuiltinFile(): File {
        val aresCliPath = AppSettingsState.instance.aresCliPath
        val jsonFilePath = "$aresCliPath/files/conf/$filename"
        return File(jsonFilePath)
    }

    fun getFileFromPath(path: String): File {
        val jsonFilePath = "$path/$filename"
        return File(jsonFilePath)
    }
}