package com.atalgaba.jbwebosstudio

import com.intellij.DynamicBundle
import com.intellij.notification.*
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.vfs.VirtualFile
import org.jetbrains.annotations.NonNls
import org.jetbrains.annotations.PropertyKey
import javax.swing.Icon

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
    fun showNotification(type: NotificationType, title: String, content: String, project: Project? = null) {
        val notificationGroup = NotificationGroup.findRegisteredGroup("webOSStudio.BalloonGroup")
        if (notificationGroup != null) {
            val notification = notificationGroup.createNotification(title, content, type)
            if (project != null) {
                notification.notify(project)
            } else {
                Notifications.Bus.notify(notification)
            }
        }
    }

    @JvmStatic
    fun showNotification(type: NotificationType, title: String, content: List<String>, project: Project? = null) {
        val notificationGroup = NotificationGroup.findRegisteredGroup("webOSStudio.BalloonGroup")
        if (notificationGroup != null) {
            val notificationContent = content[0] // Concatenate the list of strings into a single string
            val notification = notificationGroup.createNotification(title, notificationContent, type)

            // Add a notification listener to show details
            val showDetailsAction = object : NotificationAction("Show details") {
                override fun actionPerformed(e: AnActionEvent, notification: Notification) {
                    val details = content.joinToString("\n") // Concatenate the list of strings into a single string
                    val icon: Icon = when (type) {
                        NotificationType.WARNING -> {
                            Messages.getWarningIcon()
                        }

                        NotificationType.ERROR -> {
                            Messages.getErrorIcon()
                        }

                        NotificationType.INFORMATION -> {
                            Messages.getInformationIcon()
                        }

                        NotificationType.IDE_UPDATE -> {
                            Messages.getInformationIcon()
                        }
                    }
                    Messages.showMessageDialog(details, title, icon)
                }
            }
            notification.addAction(showDetailsAction)

            if (project != null) {
                notification.notify(project)
            } else {
                Notifications.Bus.notify(notification)
            }
        }
    }

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
