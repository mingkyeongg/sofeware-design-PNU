package com.example.ex03

import android.content.ContentValues.TAG
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import android.widget.Toast
import com.example.ex03.databinding.ActivityMainBinding
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val db = Firebase.firestore
    var oSysMainLoop = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adocRef = db.collection("user").document("50u8UNzYj4woP45HTIDK")
        adocRef.addSnapshotListener(com.google.firebase.firestore.EventListener<DocumentSnapshot> { snapshot, e ->
            if (snapshot != null && snapshot.exists()) {
                binding.textView.text = snapshot.data!!["xloc"].toString()
            }
        })

        if (oSysMainLoop == 0) {
            oSysMainLoop = 1
            timer(period = 1500, initialDelay = 1000)
            {
                if (oSysMainLoop != 1) {
                    cancel()
                }
                val axloc = hashMapOf("xloc" to (0..100).random())
                db.collection("user").document("50u8UNzYj4woP45HTIDK").set(axloc)
                /*성공, 실패 callback 리스너 기능 구현*/
                db.collection("user")
                    .get()
                    .addOnSuccessListener {
                        Toast.makeText(this@MainActivity, "성공", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this@MainActivity, "실패", Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }
}