package com.atalgaba.jbwebosstudio.actions

import com.atalgaba.jbwebosstudio.WebOSStudio
import com.atalgaba.jbwebosstudio.controllers.DeviceController
import com.atalgaba.jbwebosstudio.models.Device
import com.atalgaba.jbwebosstudio.util.CommandLineUtil
import com.intellij.notification.NotificationType
import com.intellij.openapi.actionSystem.*
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.progress.ProgressManager
import com.intellij.openapi.project.DefaultProjectFactory
import com.intellij.openapi.util.Ref
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.wm.ToolWindowManager
import org.jetbrains.plugins.terminal.ShellTerminalWidget
import org.jetbrains.plugins.terminal.TerminalToolWindowFactory
import org.jetbrains.plugins.terminal.TerminalView
import java.io.File
import java.io.IOException


class AresInstall : ActionGroup() {
    companion object {
        const val TAB_NAME = "Ares"
    }

    override fun getActionUpdateThread(): ActionUpdateThread {
        return ActionUpdateThread.BGT
    }

    override fun update(event: AnActionEvent) {
        val project = event.project
        if (event.place == ActionPlaces.PROJECT_VIEW_POPUP && project != null) {
            val file = event.getData(CommonDataKeys.VIRTUAL_FILE)
            event.presentation.isEnabledAndVisible = WebOSStudio.isWebOSBundleFile(file)
        }
    }

    override fun actionPerformed(event: AnActionEvent) {
        when (val project = event.project) {
            null -> thisLogger().error("Cannot run command in local terminal. Project is null")
            else -> {
                try {
                    val terminalView = TerminalView.getInstance(project)
                    val window = ToolWindowManager.getInstance(project)
                        .getToolWindow(TerminalToolWindowFactory.TOOL_WINDOW_ID)
                    val contentManager = window?.contentManager

                    val widget = when (val content = contentManager?.findContent(TAB_NAME)) {
                        null -> terminalView.createLocalShellWidget(project.basePath, TAB_NAME)
                        else -> TerminalView.getWidgetByContent(content) as ShellTerminalWidget
                    }

                    val selectedFolder = event.getData(CommonDataKeys.VIRTUAL_FILE)?.path
                    widget.executeCommand("ares-install $selectedFolder")

                } catch (e: IOException) {
                    thisLogger().error("Cannot run command in local terminal. Error:$e")
                }
            }
        }
    }



    override fun getChildren(e: AnActionEvent?): Array<AnAction> {
        val devices: List<Device> = DeviceController().getDevices()
        val actions = mutableListOf<AnAction>()
        devices.forEach {
            actions.add(object : AnAction(it.name) {
                override fun actionPerformed(e: AnActionEvent) {
                    val project = e.project
                    if (project != null) {
                        try {
                            val selectedFolder = e.getData(CommonDataKeys.VIRTUAL_FILE)?.path
                                ?: throw IOException("Cannot create package for non existing folder")
                            val exceptionRef: Ref<RuntimeException> = Ref.create(null)

                            val npmProject = DefaultProjectFactory.getInstance().defaultProject

                            val npxCommand = "ares-install"
                            val commandLine = CommandLineUtil.createNpxCommandLine(
                                npmProject,
                                npxCommand,
                                listOf("-d ${it.name}", selectedFolder),
                                File(selectedFolder).parentFile
                            )

                            val action = CommandLineUtil.createExecuteCommandLineAction(
                                npmProject, commandLine, exceptionRef, null
                            )

                            if (!ProgressManager.getInstance()
                                    .runProcessWithProgressSynchronously(action, "Installing IPK", true, null)
                            ) {
                                thisLogger().error("${commandLine.commandLineString} couldn't be executed")
                            }
                            val exception = exceptionRef.get()
                            if (exception != null) {
                                throw exception
                            }

                            // Synchronize project files
                            LocalFileSystem.getInstance().refreshIoFiles(
                                listOf(File(selectedFolder).parentFile),
                                false,
                                true,
                                null
                            )

                        } catch (e: IOException) {
                            thisLogger().error("Cannot run command in local terminal. Error:$e")
                        } catch (e: Exception) {
                            thisLogger().error("Cannot run command in local terminal. Error:$e")
                            WebOSStudio.showNotification(
                                NotificationType.ERROR,
                                "Error Installing the IPK",
                                listOf(e.localizedMessage, e.stackTrace.joinToString("\n"))
                            )
                        }
                    }
                }
            })
        }

        return actions.toTypedArray()
    }
}
