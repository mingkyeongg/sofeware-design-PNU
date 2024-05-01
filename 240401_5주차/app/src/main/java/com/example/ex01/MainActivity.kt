package com.example.ex01

import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.ex01.OneFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragButton = findViewById<Button>(R.id.fragBut1)
        val FragmentManager: FragmentManager = supportFragmentManager

        // TODO: FragmentManager 및 fragButton 변수 선언
        var onClicked = false
        fragButton.setOnClickListener{
            if (onClicked) {
                onClicked = false
                val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
                val frameLayout = supportFragmentManager.findFragmentById(R.id.fragment_content)
                transaction.remove(frameLayout!!).commit()
                // TODO: FragmentManager를 사용하여 Fragment를 제거
            }
            else {
                onClicked=true
                val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
                transaction.add(R.id.fragment_content, OneFragment()).commit()
                // TODO: FragmentManager를 사용하여 Fragment를 추가
            }
        }
    }
}

