package com.atalgaba.jbwebosstudio.util

import com.atalgaba.jbwebosstudio.enums.WebOSToolsCLIFile
import com.atalgaba.jbwebosstudio.settings.AppSettingsState
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import java.io.File

object WebOSToolsCLIUtil {
    val workingDirectory: String? =
        System.getenv("APPDATA") ?: System.getenv("HOME") ?: System.getenv("USERPROFILE")

    fun getDataDir(): String {
        if (workingDirectory == null) {
            return AppSettingsState.instance.aresCliPath + "/files/conf"
        }

        val aresCliPath = AppSettingsState.instance.aresCliPath
        val jsonFilePath = "$aresCliPath/files/conf/config.json"
        val configContent: String = File(jsonFilePath).readText()
        val configJson = Json.parseToJsonElement(configContent)
        val dataDir: String = configJson.jsonObject["dataDir"]?.jsonPrimitive?.content ?: ".webos/ose"
        return "$workingDirectory/$dataDir"
    }

    fun getFile(webosFile: WebOSToolsCLIFile): File {
        return webosFile.getFileFromPath(getDataDir())
    }
}