package com.atalgaba.jbwebosstudio.util

import com.atalgaba.jbwebosstudio.WebOSStudio
import com.atalgaba.jbwebosstudio.settings.AppSettingsState
import com.intellij.execution.ExecutionException
import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.execution.configurations.GeneralCommandLine.ParentEnvironmentType
import com.intellij.execution.process.*
import com.intellij.javascript.nodejs.NodeCommandLineUtil
import com.intellij.javascript.nodejs.interpreter.NodeJsInterpreter
import com.intellij.javascript.nodejs.npm.NpmUtil
import com.intellij.javascript.nodejs.util.NodePackageRef
import com.intellij.notification.NotificationType
import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.progress.ProgressManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Disposer
import com.intellij.openapi.util.Key
import com.intellij.openapi.util.Ref
import com.intellij.util.Alarm
import java.io.File
import java.util.concurrent.TimeUnit


object CommandLineUtil {

    @JvmStatic
    fun createExecuteCommandLineAction(
        project: Project?,
        commandLine: GeneralCommandLine,
        exceptionRef: Ref<RuntimeException>,
        currentIndicator: ProgressIndicator?
    ): Runnable {
        return Runnable {
            val indicator =
                currentIndicator ?: ProgressManager.getInstance().progressIndicator
            indicator.text = commandLine.commandLineString
            val output = ProcessOutput()
            try {
                val listener: ProcessListener = object : ProcessAdapter() {
                    override fun onTextAvailable(event: ProcessEvent, outputType: Key<*>) {
                        val text = event.text.trim { it <= ' ' }
                        if (outputType === ProcessOutputTypes.STDERR || outputType === ProcessOutputTypes.STDOUT) {
                            indicator.text2 = text
                        }
                    }
                }
                val processHandler: OSProcessHandler = KillableColoredProcessHandler(commandLine)


                val alarm = if (project == null) null else Alarm(Alarm.ThreadToUse.POOLED_THREAD, project)
                alarm?.addRequest(object : Runnable {
                    override fun run() {
                        if (!processHandler.isProcessTerminated) {
                            if (indicator.isCanceled) {
                                processHandler.destroyProcess()
                            } else {
                                alarm.addRequest(this, TimeUnit.SECONDS.toMillis(1))
                            }
                        }
                    }
                }, TimeUnit.SECONDS.toMillis(1))

                processHandler.addProcessListener(object : ProcessAdapter() {
                    override fun onTextAvailable(event: ProcessEvent, outputType: Key<*>) {
                        if (outputType === ProcessOutputTypes.STDERR) {
                            output.appendStderr(event.text)
                        } else if (outputType !== ProcessOutputTypes.SYSTEM) {
                            output.appendStdout(event.text)
                        }
                    }

                    override fun processTerminated(event: ProcessEvent) {
                        if (alarm != null) {
                            Disposer.dispose(alarm)
                        }
                    }
                })
                processHandler.addProcessListener(listener)
                processHandler.startNotify()
                if (processHandler.waitFor((TimeUnit.MINUTES.toMillis(10).toInt()).toLong())) {
                    try {
                        output.exitCode = processHandler.process.exitValue()
                    } catch (e: Exception) {
                        throw ExecutionException(e)
                    }
                } else {
                    processHandler.destroyProcess()
                    output.setTimeout()
                }
            } catch (e: ExecutionException) {
                exceptionRef.set(java.lang.RuntimeException("Cannot execute " + commandLine.commandLineString, e))
                return@Runnable
            }
            var errorMessage: String? = null
            if (output.isTimeout) {
                errorMessage = """
                    Time limit exceeded for command:
                    ${commandLine.commandLineString}
                    """.trimIndent()
            }
            if (output.exitCode != 0) {
                errorMessage = """Failed command:
${commandLine.commandLineString}""" + "\nExit code: " + output.exitCode
            }
            if (errorMessage != null) {
                if (output.stdout.isNotEmpty()) {
                    errorMessage += """
                        
                        Standard output:
                        ${output.stdout}
                        """.trimIndent()
                }
                if (output.stderr.isNotEmpty()) {
                    errorMessage += """
                        
                        Standard error:
                        ${output.stderr}
                        """.trimIndent()
                }
                exceptionRef.set(java.lang.RuntimeException(errorMessage))
            } else {
                WebOSStudio.showNotification(NotificationType.INFORMATION, "Success", output.stdoutLines)
            }
        }
    }

    fun createNpxCommandLine(
        project: Project,
        command: String,
        parameters: List<String>? = null,
        workingDirectory: File? = null
    ): GeneralCommandLine {
        val files = AppSettingsState.instance

        val interpreter: NodeJsInterpreter = checkNotNull(files.getInterpreter())

        val npmPackage =
            NpmUtil.resolvePackageRefOrThrow(NodePackageRef.create(command), project, interpreter)

        val commandLine = GeneralCommandLine(npmPackage.systemDependentPath)
        if (parameters != null) {
            commandLine.addParameters(parameters)
        }
        commandLine.withParentEnvironmentType(ParentEnvironmentType.CONSOLE)
        NodeCommandLineUtil.configureUsefulEnvironment(commandLine)

//        val cliJsFilePath = NpmUtil.getValidNpmCliJsFilePath(npmPackage, null)
//        commandLine.addParameter(cliJsFilePath)

        if (workingDirectory != null) {
            commandLine.withWorkDirectory(workingDirectory)
        }

        try {
            return commandLine
        } catch (e: ExecutionException) {
            throw java.lang.RuntimeException(e.message, e)
        }
    }
}