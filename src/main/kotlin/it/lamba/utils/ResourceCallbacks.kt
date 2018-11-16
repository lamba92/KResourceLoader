package it.lamba.utils

import java.io.File

/**
 *  Interface used by [getResourceCallback].
 */
interface ResourceCallbacks {
    /**
     * Called when the resource is ready.
     * @param resource The resource where the resource should be places.
     */
    fun onFileReady(resource: File)

    /**
     * Called when an error occurs when loading the file.
     * @param exception The thrown exception.
     */
    fun onError(exception: Exception)
}
