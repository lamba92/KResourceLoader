# KResourceLoader [![Build Status](https://travis-ci.org/lamba92/KResourceLoader.svg?branch=master)](https://travis-ci.org/lamba92/KResourceLoader) [![](https://jitpack.io/v/lamba92/kresourceloader.svg)](https://jitpack.io/#lamba92/kresourceloader)

A convenient way to retrieve resources in a JVM project. Especially useful when the project is launching from a jar with bundled resources.

Written in Kotlin with ❤️.

## Usage

To retrieve a resource synchronously:

```
fun main(){
    val resource = getResource("file.txt")
}
```

Asynchronously:

```
fun main() = runBlocking {
    val deferredResource = getResourceAsync("file.txt")
    val resource = deferredResource.await()
}
```

Or with callbacks:

```
fun main(){
    val callbacks = object : ResourceCallbacks {
    
        override fun onFileReady(resource: File) {
                //use your file here!
            }
        
            override fun onError(exception: Exception) {
                throw exception
            }
    }
    
    val job = getResourceCallbacks("file.txt", 
            callbacks = callbacks)
}
```

## Installing [![](https://jitpack.io/v/lamba92/kresourceloader.svg)](https://jitpack.io/#lamba92/kresourceloader)

Add the [JitPack.io](http://jitpack.io) repository to the project `build.grade`:
```
repositories {
    maven { url 'https://jitpack.io' }
}
```

Then import the latest version in the `build.gradle` of the modules you need:

```
dependencies {
    implementation 'com.github.lamba92:kresourceloader:{latest_version}'
}
```

If using Gradle Kotlin DSL:
```
repositories {
    maven(url = "https://jitpack.io")
}
...
dependencies {
    implementation("com.github.lamba92", "kresourceloader", "{latest_version}")
}

