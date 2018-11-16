package it.lamba.utils.test

import it.lamba.utils.ResourceCallbacks
import it.lamba.utils.getResource
import it.lamba.utils.getResourceAsync
import it.lamba.utils.getResourceCallback
import kotlinx.coroutines.runBlocking
import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.fail

class KRTTest : ResourceCallbacks {
    private val classLoader = this::class.java.classLoader

    @Test
    fun testResource(){
        val string = getResource("test.txt").readText()
        assertEquals( "Hello world!", string)
    }

    @Test
    fun testResourceAsync() = runBlocking {
        val string = getResourceAsync("test.txt").await().readText()
        assertEquals("Hello world!", string)
    }

    @Test
    fun testResourceCallback() = runBlocking {
        getResourceCallback("test.txt", callbacks = this@KRTTest).join()
    }

    override fun onFileReady(resource: File) {
        assertEquals("Hello world!", resource.readText())
    }

    override fun onError(exception: Exception) {
        fail(exception.stackTrace.toString())
    }

}
