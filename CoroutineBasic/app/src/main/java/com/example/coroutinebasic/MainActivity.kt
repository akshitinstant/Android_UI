// coroutineScope, Suspend Function

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
import com.example.coroutinebasic.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    private val mainScope = MainScope()     //MainScope() = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


//scope: activity, launch-starts a new asynchronous coroutine
        lifecycleScope.launch {     // by default MainScope
            Log.d("coroutine", "Running: ${Thread.currentThread().name}")
            val result =
                withContext(Dispatchers.IO) {    //changes the context of the coroutine to the background thread, switching threads
                    delay(2000)     // suspends coroutine for 2 second
                    "Data Loaded from lifecycleScope"
                }
            Toast.makeText(this@MainActivity, "LifecycleScope Running!", Toast.LENGTH_SHORT).show()
            binding.myTxt.text = result
            Log.d("coroutine", "LifecycleScope -> UI Updated on: ${Thread.currentThread().name}")
        }


// GlobalScope Example
        GlobalScope.launch {        // avoid UI changes in global scope, not aware of lifecycle, can lead to memory leak
            delay(4000)
            Log.d("coroutine", "Running on: ${Thread.currentThread().name}")
            withContext(Dispatchers.Main) {     // Switching Threads,  withContext is of Blocking Nature
                binding.myTxt.text = getString(R.string.data_loaded_from_globalscope)
                Toast.makeText(this@MainActivity, "GlobalScope Running!", Toast.LENGTH_SHORT).show()
            }
        }


// CoroutineScope Example
        coroutineScope.launch {
            delay(6000)
            Log.d("coroutine", "Running on: ${Thread.currentThread().name}")
            var txt = getMyText()
            binding.myTxt.text = txt
            Toast.makeText(this@MainActivity, "CoroutineScope Running!", Toast.LENGTH_SHORT).show()
        }


// MainScope Example
        mainScope.launch {
            delay(8000)
            binding.myTxt.text = getString(R.string.data_loaded_from_mainscope)
            Log.d("coroutine", "Running on: ${Thread.currentThread().name}")
            Toast.makeText(this@MainActivity, "MainScope Running!", Toast.LENGTH_SHORT).show()
        }

    }

    suspend fun getMyText(): String {
        delay(5000)
        return "Text from suspend function (CoroutineScope)"
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel()
        mainScope.cancel()
    }

    fun toAct2(view: View) {
        startActivity(Intent(this, MainActivity2::class.java))
    }

fun toAct3(view: View) {
        startActivity(Intent(this, MainActivity3::class.java))
    }

    fun toAct4(view: View) {
        startActivity(Intent(this, MainActivity4::class.java))
    }

}