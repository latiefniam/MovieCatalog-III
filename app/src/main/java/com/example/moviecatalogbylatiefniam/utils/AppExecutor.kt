package com.example.moviecatalogbylatiefniam.utils


import android.os.Looper
import android.os.Handler
import androidx.annotation.VisibleForTesting
import java.util.concurrent.Executor
import java.util.concurrent.Executors


class AppExecutor @VisibleForTesting
constructor(
    private val diskIO: Executor,
    private val networkIO: Executor,
    private val mainThread: Executor
) {
    companion object{
        private const val COUNT = 3
    }
    constructor():this(
        Executors.newSingleThreadExecutor(),
        Executors.newFixedThreadPool(COUNT),
        MainThreadExecutor()
    )

    fun diskIO(): Executor = diskIO
    fun mainThread():Executor= mainThread

    private class MainThreadExecutor:Executor{
        private val mainThreadHandler = Handler(Looper.getMainLooper())
        override fun execute(command: Runnable) {
          mainThreadHandler.post(command)
        }


    }
}