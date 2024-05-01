package com.example.ex01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ex01.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.readBtn.setOnClickListener {
            readFirebase()
        }

        binding.writeBtn.setOnClickListener {
            var name = if (binding.name.text.isEmpty()) "null" else binding.name.text.toString()
            var email = if (binding.email.text.isEmpty()) "null" else binding.email.text.toString()
            var age = if (binding.age.text.isEmpty()) 0 else binding.age.text.toString().toInt()

            writeFirebase(name, email, age)
        }
    }

    private fun readFirebase() {
        db.collection("user").document("8Gf8RXbE3cB88pbNYPog")
            .get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    binding.output1.append("${document.data}\n")
                } else {
                    binding.output1.append("No document found\n")
                }
            }
            .addOnFailureListener {
                binding.output1.append("Failure \n")
            }
    }

    private fun writeFirebase(name: String, email: String, age: Int) {
        val user = mapOf(
            "name" to name,
            "email" to email,
            "age" to age
        )
        // Either set or add a new document depending on your requirement:
        db.collection("user").document("8Gf8RXbE3cB88pbNYPog").set(user)
            .addOnSuccessListener {
                binding.output1.append("Document successfully written!\n")
            }
            .addOnFailureListener {
                binding.output1.append("Error writing document\n")
            }
    }
}
