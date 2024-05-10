package com.atalgaba.jbwebosstudio.actions

import com.atalgaba.jbwebosstudio.WebOSStudio
import com.atalgaba.jbwebosstudio.util.CommandLineUtil
import com.intellij.notification.NotificationType
import com.intellij.openapi.actionSystem.ActionPlaces
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.progress.ProgressManager
import com.intellij.openapi.project.DefaultProjectFactory
import com.intellij.openapi.util.Ref
import com.intellij.openapi.vfs.LocalFileSystem
import java.io.File
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
                    val selectedFolder = event.getData(CommonDataKeys.VIRTUAL_FILE)?.path
                        ?: throw IOException("Cannot create package for non existing folder")

                    val exceptionRef: Ref<RuntimeException> = Ref.create(null)

                    val npmProject = DefaultProjectFactory.getInstance().defaultProject;

                    val npxCommand = "ares-package"
                    val commandLine = CommandLineUtil.createNpxCommandLine(
                        npmProject,
                        npxCommand,
                        listOf(selectedFolder),
                        File(selectedFolder).parentFile
                    )

                    val action = CommandLineUtil.createExecuteCommandLineAction(
                        npmProject, commandLine, exceptionRef, null
                    )

                    if (!ProgressManager.getInstance()
                            .runProcessWithProgressSynchronously(action, "Creating IPK", true, null)
                    ) {
                        thisLogger().error("${commandLine.commandLineString} couldn't be executed")
                    }
                    val exception = exceptionRef.get();
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
                    WebOSStudio.showNotification(NotificationType.ERROR, "Error", e.localizedMessage, project)
                }
            }
        }
    }
}
