// runBlocking, Job, async, deferred
package com.example.coroutinebasic

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.coroutinebasic.databinding.ActivityMain2Binding
import com.example.coroutinebasic.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        Log.d("runB", "Main thread started")
        for (i in 1..3) {
            Log.d("runB", "In main thread")
        }

        /*  // without runBlocking, here launch creates coroutine which run outside the main thread.
                    coroutineScope.launch {
                        delay(2000)
                        Log.d("runB", "Coroutine is done")
                    }*/

// with runBlocking, it stops the main thread until the mentioned process completes.
        /* not recommended to use runBlocking on main thread(onCreate()), if the process took more time, then the UI/Application may freeze and even crash.*/
        runBlocking {
            launch {
                delay(2000)
                Log.d("runB", "runBlocking Coroutine started")
                launchJob()
            }
        }


        for (i in 4..6) {
            Log.d("runB", "In main thread")
        }
        Log.d("runB", "Main thread end")

        CoroutineScope(Dispatchers.IO).async {
            Log.d("asyncBuild", "Bg. Coroutine started")
            asyncDef()
            Log.d("asyncBuild", "Bg. Coroutine done")
        }

    }

    suspend fun launchJob() {
        var job = coroutineScope.launch {
            delay(2000)
            Log.d("runB", "launch returning Job instance")
        }
//        job.cancel()
        Log.d("runB", "Active?=${job.isActive} - Waiting for job...")
        job.join()
        Log.d("runB", "Job completed?=${job.isCompleted}")
    }

    suspend fun asyncDef() {
        val def = CoroutineScope(Dispatchers.IO).async {
            Log.d("asyncBuild", "Async coroutine created")
            delay(2000)
            var value = async { 3/*function call*/ }
            "returnValue"
        }
        Log.d("asyncBuild", "Active?=${def.isActive}, Completed?=${def.isCompleted}")
        Log.d("asyncBuild", def.await())    // returning value
        Log.d("asyncBuild", "Async coroutine done, Active?=${def.isActive}, Completed?=${def.isCompleted}"
        )
    }

    fun toAct3(view: View) {
        startActivity(Intent(this, MainActivity3::class.java))
    }


}