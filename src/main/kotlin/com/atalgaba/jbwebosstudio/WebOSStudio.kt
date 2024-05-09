package com.atalgaba.jbwebosstudio

import com.intellij.DynamicBundle
import com.intellij.openapi.vfs.VirtualFile
import org.jetbrains.annotations.NonNls
import org.jetbrains.annotations.PropertyKey

@NonNls
private const val BUNDLE = "messages.WebOSStudio"

object WebOSStudio : DynamicBundle(BUNDLE) {

    @Suppress("unused")
    @JvmStatic
    fun message(@PropertyKey(resourceBundle = BUNDLE) key: String, vararg params: Any) =
        getMessage(key, *params)

    @Suppress("unused")
    @JvmStatic
    fun messagePointer(@PropertyKey(resourceBundle = BUNDLE) key: String, vararg params: Any) =
        getLazyMessage(key, *params)

    @JvmStatic
    fun isWebOSTemplateDirectory(directory: VirtualFile?): Boolean {
        if (directory == null || !directory.isDirectory) {
            return false
        }
        val files = directory.children
        return files.any { it.name == "appinfo.json" }
    }

    @JvmStatic
    fun isWebOSBundleFile(file: VirtualFile?): Boolean {
        if (file == null || !file.isValid || file.isDirectory) {
            return false
        }
        return file.name.endsWith(".ipk")
    }
}
