package com.websocket.demo.extension

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.util.zip.GZIPOutputStream


@Throws(IOException::class)
fun ByteArray.compress(): ByteArray {
    println("before compress size: " + this.size)
    val out: ByteArray
    val bi = ByteArrayInputStream(this)
    val bo = ByteArrayOutputStream()
    val go = GZIPOutputStream(bo)
    val bb = ByteArray(1024)
    while (bi.read(bb) != -1) go.write(bb)
    out = bo.toByteArray()
    println("after compress size: " + out.size)
    return out
}