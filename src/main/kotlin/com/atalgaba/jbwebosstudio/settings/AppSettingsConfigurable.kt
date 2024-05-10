// Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.atalgaba.jbwebosstudio.settings

import com.atalgaba.jbwebosstudio.settings.AppSettingsState.Companion.instance
import com.intellij.javascript.nodejs.interpreter.NodeJsInterpreterRef
import com.intellij.openapi.options.Configurable
import com.intellij.openapi.util.text.StringUtil
import org.jetbrains.annotations.Nls
import javax.swing.JComponent

/**
 * Provides controller functionality for application settings.
 */
internal class AppSettingsConfigurable : Configurable {
    private var mySettingsComponent: AppSettingsComponent? = null

    // A default constructor with no arguments is required because this implementation
    // is registered in an applicationConfigurable EP
    override fun getDisplayName(): @Nls(capitalization = Nls.Capitalization.Title) String {
        @Suppress("DialogTitleCapitalization")
        return "webOS Studio Settings"
    }

    override fun getPreferredFocusedComponent(): JComponent {
        return mySettingsComponent!!.preferredFocusedComponent
    }

    override fun createComponent(): JComponent {
        mySettingsComponent = AppSettingsComponent()
        return mySettingsComponent!!.panel
    }

    override fun isModified(): Boolean {
        val settings = instance

        return !StringUtil.equals(
            StringUtil.notNullize(settings.aresCliPath),
            StringUtil.notNullize(mySettingsComponent!!.aresCliPathText)
        ) || !StringUtil.equals(
            StringUtil.notNullize(settings.getNodeInterpreterRefName()),
            StringUtil.notNullize(getNodeInterpreterRefName())
        )
    }

    override fun apply() {
        val settings = instance
        settings.aresCliPath = mySettingsComponent!!.aresCliPathText
        settings.nodeExePath = getNodeInterpreterRefName()
    }

    private fun getNodeInterpreterRefName(): String? {
        val interpreterRef: NodeJsInterpreterRef = mySettingsComponent!!.nodeTextField.interpreterRef
        return if (interpreterRef.isProjectRef) null else interpreterRef.referenceName
    }

    override fun reset() {
        val settings = instance
        mySettingsComponent!!.aresCliPathText = settings.aresCliPath
        mySettingsComponent!!.nodeTextField.interpreterRef =
            NodeJsInterpreterRef.create(settings.getNodeInterpreterRefName())
    }

    override fun disposeUIResources() {
        mySettingsComponent = null
    }
}