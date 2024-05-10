// Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.atalgaba.jbwebosstudio.settings

import com.intellij.javascript.nodejs.interpreter.NodeJsInterpreterRef
import com.intellij.javascript.nodejs.interpreter.local.NodeJsLocalInterpreter
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManager
import com.intellij.openapi.util.text.StringUtil
import com.intellij.util.xmlb.XmlSerializerUtil

/**
 * Supports storing the application settings in a persistent way.
 * The [State] and [Storage] annotations define the name of the data and the file name where
 * these persistent application settings are stored.
 */
@State(
    name = "com.atalgaba.jbwebosstudio.settings.AppSettingsState",
    storages = [Storage("WebOSStudioSettingsPlugin.xml")]
)
internal class AppSettingsState : WOSNodeFiles, PersistentStateComponent<AppSettingsState> {
    @JvmField
    var aresCliPath: String? = null

    @JvmField
    var nodeExePath: String? = null

    fun getNodeInterpreterRefName(): String? {
        if (!StringUtil.isEmpty(nodeExePath)) {
            return nodeExePath
        }

        return null
    }

    override fun getState(): AppSettingsState {
        return this
    }

    override fun loadState(state: AppSettingsState) {
        XmlSerializerUtil.copyBean(state, this)
    }

    override fun getInterpreter(): NodeJsLocalInterpreter? {
        val defaultProject: Project = ProjectManager.getInstance().defaultProject
        return NodeJsLocalInterpreter.tryCast(
            NodeJsInterpreterRef.create(getNodeInterpreterRefName()).resolve(defaultProject)
        )
    }

    companion object {
        @JvmStatic
        val instance: AppSettingsState
            get() = ApplicationManager.getApplication().getService(
                AppSettingsState::class.java
            )
    }


}