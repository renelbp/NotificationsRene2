package com.example.notificationsrene2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.notificationsrene2.databinding.ActivityMain2Binding

lateinit var binding: ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val message = intent.getStringExtra("message")
        if (!message.isNullOrEmpty()) {
            binding.textView.text = message
        }
    }
}