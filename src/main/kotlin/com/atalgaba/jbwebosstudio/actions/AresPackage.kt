package com.atalgaba.jbwebosstudio.actions

import com.atalgaba.jbwebosstudio.WebOSStudio
import com.intellij.openapi.actionSystem.ActionPlaces
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.wm.ToolWindowManager
import org.jetbrains.plugins.terminal.ShellTerminalWidget
import org.jetbrains.plugins.terminal.TerminalToolWindowFactory
import org.jetbrains.plugins.terminal.TerminalView
import java.io.IOException


@Suppress("MissingActionUpdateThread")
class AresPackage : AnAction() {
    companion object {
        const val TAB_NAME = "Ares"
    }

    override fun update(event: AnActionEvent) {
        val project = event.project
        if (event.place == ActionPlaces.PROJECT_VIEW_POPUP && project != null) {
            val file = event.getData(CommonDataKeys.VIRTUAL_FILE)
            event.presentation.isEnabledAndVisible = WebOSStudio.isWebOSTemplateDirectory(file)
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
                    widget.executeCommand("ares-package $selectedFolder")

                } catch (e: IOException) {
                    thisLogger().error("Cannot run command in local terminal. Error:$e")
                }
            }
        }
    }
}
