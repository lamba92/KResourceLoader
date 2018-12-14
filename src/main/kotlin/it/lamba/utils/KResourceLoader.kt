@file:JvmName("KResourceLoader")

package it.lamba.utils

import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.Deferred
import java.io.File

/**
 * Retrieves a resource from the given class loader or default one.
 * @param name The relative path of the resource.
 * @param classLoader The classloader from which load the resource.
 * @param file The file where the resource should be places.
 * @return The [file] with the resource.
 */
@JvmOverloads
fun getResource(name: String,
                file: File = createTempFile().apply { deleteOnExit() },
                classLoader: ClassLoader = Thread.currentThread().contextClassLoader
) = classLoader.getResourceAsStream(name)!!.let {
    file.apply { writeBytes(it.readBytes()) }
}

/**
 * Asynchronous version of [getResource]. Retrieves a resource from the given class loader or default one.
 * @param name The relative path of the resource.
 * @param classLoader The classloader from which load the resource.
 * @param file The file where the resource should be places.
 * @return A [Deferred] to the [file] with the resource.
 */
@JvmOverloads
fun getResourceAsync(name: String,
                     file: File = createTempFile().apply { deleteOnExit() },
                     classLoader: ClassLoader = Thread.currentThread().contextClassLoader
) = GlobalScope.async(IO) { getResource(name, file, classLoader) }

/**
 * Callback version of [getResourceAsync]. Retrieves a resource from the given class loader or default one.
 * @param name The relative path of the resource.
 * @param callbacks The callbacks used to notify the caller.
 * @param classLoader The classloader from which load the resource.
 * @param file The file where the resource should be places.
 * @return A [Deferred] to the [file] with the resource.
 */
@JvmOverloads
fun getResourceCallback(
    name: String,
    file: File = createTempFile().apply { deleteOnExit() },
    classLoader: ClassLoader = Thread.currentThread().contextClassLoader,
    callbacks: ResourceCallbacks
) = GlobalScope.launch(IO) {
    try {
        callbacks.onFileReady(getResource(name, file, classLoader))
    } catch (e: Exception){
        callbacks.onError(e)
    }
}