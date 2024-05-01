package com.example.ex03

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ex03.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import java.text.DecimalFormat
import java.time.LocalTime



class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val stopwatchChannel = Channel<Long>()
    private var job: Job? = null
    private var isPause = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var startTime = 0L
        var pauseTime = 0L

        binding.start.setOnClickListener {
            if (!isPause && (job == null || job!!.isCancelled)) {
                startTime = System.currentTimeMillis() - pauseTime
                job = startStopwatch(startTime)
            }
            if (isPause) {
                Toast.makeText(this, "Stopwatch is not stopped now!!!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.stop.setOnClickListener {
            job?.cancel()
            stopwatchChannel.trySend(0L).isSuccess
            pauseTime = 0L
            isPause = false
        }

        binding.pause.setOnClickListener {
            job?.cancel()
            job = null
            isPause = true
        }

        binding.resume.setOnClickListener {
            if (isPause && (job == null || job!!.isCancelled)) {
                startTime = System.currentTimeMillis() - pauseTime
                job = startStopwatch(startTime)
            }
        }

        CoroutineScope(Dispatchers.Main).launch {
            stopwatchChannel.consumeEach {
                pauseTime = it
                binding.time.text = formatTime(it)
            }
        }
    }

    private fun startStopwatch(startTime: Long): Job {
        return CoroutineScope(Dispatchers.Default).launch {
            while (isActive) {
                val currentTime = System.currentTimeMillis() - startTime
                stopwatchChannel.send(currentTime)
                delay(1000)
            }
        }
    }

    private fun formatTime(millis: Long): String {
        val h = millis / 1000 / 3600
        val m = millis / 1000 % 3600 / 60
        val s = millis / 1000 % 60
        return String.format("%02d:%02d:%02d", h, m, s)
    }

    override fun onDestroy() {
        job?.cancel()
        super.onDestroy()
    }
}
