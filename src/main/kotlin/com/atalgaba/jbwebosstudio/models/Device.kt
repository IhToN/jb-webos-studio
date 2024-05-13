package com.atalgaba.jbwebosstudio.models

import kotlinx.serialization.Serializable

@Serializable
data class PrivateKey(val openSsh: String)

@Serializable
data class Device(
    val order: String = "0",
    val default: Boolean = false,
    val profile: String?,
    val name: String?,
    val description: String?,
    val host: String?,
    val port: Int?,
    val username: String?,
    val privateKey: PrivateKey?,
    val indelible: Boolean = false
)