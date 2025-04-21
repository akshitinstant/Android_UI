// coroutine hierarchy , cooperative cancellation, Coroutine Context
package com.example.coroutinebasic

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main3)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        lifecycleScope.launch {
            execute()
        }

        CoroutineScope(Dispatchers.IO).launch {
            executeTask()
        }

        coroutineContextEx()

        exceptionEx()
    }


    suspend fun execute() {
        val parentJob = MainScope().launch {
            Log.d("hierarchy", "Parent Job Started")
            val childJob = launch(Dispatchers.IO) {
                Log.d("hierarchy", "Child Job Started")
                delay(2000)
                Log.d("hierarchy", "Child Job Ended")
            }
//            childJob.cancel()     //cancels child job only
            delay(3000)
            Log.d("hierarchy", "Parent Job Ended")
        }
         /*// deletes both parent and child
        delay(1000)
        parentJob.cancel()*/
        parentJob.join()
        Log.d("hierarchy", "Parent Completed: ${parentJob.isCompleted}")
    }


    suspend fun executeTask() {
        val job = CoroutineScope(Dispatchers.IO).launch {
            for (i in 1..100) {
                if (isActive) {
                    executeLongRunningTask()
                    Log.d("cooperativeCancel", i.toString())
                }
            }
        }
        delay(1000)
        Log.d("cooperativeCancel", "Cancel Job")
//        job.cancel()
        job.cancelAndJoin()   // better resource clean up
        Log.d("cooperativeCancel", "Job Complete")
    }

    fun executeLongRunningTask() {
        for (i in 1..100000000L) {
        }
    }


    fun coroutineContextEx() {
        CoroutineScope(Dispatchers.IO + CoroutineName("MyCo") + CoroutineExceptionHandler { _, exception ->
            Log.d("coContext","Caught by CoroutineExceptionHandler: ${exception.message}") }).launch {
            Log.d("coContext", "${coroutineContext[CoroutineName]}")
        }

        runBlocking {
            launch(CoroutineName("MyCustomCoroutine")) {
                Log.d("coContext", "Name: ${coroutineContext[CoroutineName.Key]}")
            }
        }
    }

    fun exceptionEx() {
        val handler = CoroutineExceptionHandler { _, exception ->
            Log.d("excepEx","Caught by CoroutineExceptionHandler: ${exception.message}")
        }
        CoroutineScope(Dispatchers.IO).launch(handler) {
            Log.d("excepEx", "Parent1 coroutine started")
            launch {
                throw RuntimeException("Ooooooooops")
                Log.d("excepEx", "Sub1: Running")

            }

            launch {
                Log.d("excepEx", "Sub2: Still runs despite Sub1 crash")
            }
            Log.d("excepEx", "Parent1 coroutine complete")
        }
        CoroutineScope(Dispatchers.IO).launch {
            Log.d("excepEx", "Parent2 coroutine started")
        }
    }

}