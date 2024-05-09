package com.atalgaba.jbwebosstudio.actions

import com.atalgaba.jbwebosstudio.WebOSStudio
import com.atalgaba.jbwebosstudio.models.Device
import com.atalgaba.jbwebosstudio.settings.AppSettingsState
import com.intellij.openapi.actionSystem.ActionGroup
import com.intellij.openapi.actionSystem.ActionPlaces
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.wm.ToolWindowManager
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import org.jetbrains.plugins.terminal.ShellTerminalWidget
import org.jetbrains.plugins.terminal.TerminalToolWindowFactory
import org.jetbrains.plugins.terminal.TerminalView
import java.io.File
import java.io.IOException


@Suppress("MissingActionUpdateThread")
class AresInstall : ActionGroup() {
    companion object {
        const val TAB_NAME = "Ares"
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

    private val json = Json { ignoreUnknownKeys = true }

    override fun getChildren(e: AnActionEvent?): Array<AnAction> {
        val aresCliPath = AppSettingsState.instance.aresCliPath
        val jsonFilePath = "$aresCliPath/files/conf/novacom-devices.json"
        val jsonContent = File(jsonFilePath).readText()
        val devices: List<Device> = json.decodeFromString(ListSerializer(Device.serializer()), jsonContent)
        val actions = mutableListOf<AnAction>()
        devices.forEach {
            actions.add(object : AnAction(it.name) {
                override fun actionPerformed(e: AnActionEvent) {
                    val project = e.project
                    if (project != null) {
                        try {
                            val terminalView = TerminalView.getInstance(project)
                            val window = ToolWindowManager.getInstance(project)
                                .getToolWindow(TerminalToolWindowFactory.TOOL_WINDOW_ID)
                            val contentManager = window?.contentManager

                            val widget = when (val content = contentManager?.findContent(TAB_NAME)) {
                                null -> terminalView.createLocalShellWidget(project.basePath, TAB_NAME)
                                else -> TerminalView.getWidgetByContent(content) as ShellTerminalWidget
                            }

                            val selectedFolder = e.getData(CommonDataKeys.VIRTUAL_FILE)?.path

                            widget.executeCommand("ares-install -d ${it.name} $selectedFolder")

                        } catch (e: IOException) {
                            thisLogger().error("Cannot run command in local terminal. Error:$e")
                        }
                    }
                }
            })
        }

        return actions.toTypedArray()
    }
}
