// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package com.atalgaba.jbwebosstudio.settings

import com.intellij.javascript.nodejs.interpreter.NodeJsInterpreterField
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
import com.intellij.openapi.project.ProjectManager
import com.intellij.openapi.ui.TextFieldWithBrowseButton
import com.intellij.ui.components.JBLabel
import com.intellij.util.ui.FormBuilder
import javax.swing.JComponent
import javax.swing.JPanel

/**
 * Supports creating and managing a [JPanel] for the Settings Dialog.
 */
class AppSettingsComponent {
    val project = ProjectManager.getInstance().defaultProject
    val panel: JPanel
    private val myAresCliPathText = TextFieldWithBrowseButton()
    private val myNodeTextField: NodeJsInterpreterField = NodeJsInterpreterField(project, false)

    init {
        @Suppress("DialogTitleCapitalization")
        myAresCliPathText.addBrowseFolderListener(
            "Select @webos-tools/cli Folder",
            null,
            null,
            FileChooserDescriptorFactory.createSingleFolderDescriptor()
        )
        @Suppress("DialogTitleCapitalization")
        panel = FormBuilder.createFormBuilder()
            .addLabeledComponent(NodeJsInterpreterField.getLabelTextForComponent(), myNodeTextField)
            .addLabeledComponent(JBLabel("@webos-tools/cli Folder"), myAresCliPathText, 1, false)
            .addComponentFillVertically(JPanel(), 0)
            .panel
    }

    val preferredFocusedComponent: JComponent
        get() = myAresCliPathText

    var aresCliPathText: String?
        get() = myAresCliPathText.text
        set(newText) {
            myAresCliPathText.setText(newText)
        }

    val nodeTextField: NodeJsInterpreterField
        get() = myNodeTextField

}