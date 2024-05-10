package com.atalgaba.jbwebosstudio.settings

import com.intellij.javascript.nodejs.interpreter.local.NodeJsLocalInterpreter

interface WOSNodeFiles {
    fun getInterpreter(): NodeJsLocalInterpreter?
}