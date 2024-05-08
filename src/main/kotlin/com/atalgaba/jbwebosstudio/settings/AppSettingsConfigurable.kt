// Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.atalgaba.jbwebosstudio.settings

import com.atalgaba.jbwebosstudio.settings.AppSettingsState.Companion.instance
import com.intellij.openapi.options.Configurable
import org.jetbrains.annotations.Nls
import javax.swing.JComponent

/**
 * Provides controller functionality for application settings.
 */
internal class AppSettingsConfigurable : Configurable {
    private var mySettingsComponent: AppSettingsComponent? = null

    // A default constructor with no arguments is required because this implementation
    // is registered in an applicationConfigurable EP
    override fun getDisplayName(): @Nls(capitalization = Nls.Capitalization.Title) String? {
        return "webOS Studio Settings"
    }

    override fun getPreferredFocusedComponent(): JComponent? {
        return mySettingsComponent!!.preferredFocusedComponent
    }

    override fun createComponent(): JComponent? {
        mySettingsComponent = AppSettingsComponent()
        return mySettingsComponent!!.panel
    }

    override fun isModified(): Boolean {
        val settings = instance
        var modified = mySettingsComponent!!.aresCliPathText != settings.aresCliPath
        return modified
    }

    override fun apply() {
        val settings = instance
        settings.aresCliPath = mySettingsComponent!!.aresCliPathText
    }

    override fun reset() {
        val settings = instance
        mySettingsComponent!!.aresCliPathText = settings.aresCliPath
    }

    override fun disposeUIResources() {
        mySettingsComponent = null
    }
}