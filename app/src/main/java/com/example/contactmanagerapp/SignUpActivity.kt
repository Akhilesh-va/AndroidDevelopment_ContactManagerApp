package com.example.contactmanagerapp

import UserData
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.contactmanagerapp.databinding.ActivitySignUpBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class SignUpActivity : AppCompatActivity() {
    lateinit var binding : ActivitySignUpBinding
    lateinit var databse : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvDirectLogin.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.btnSignUp.setOnClickListener{
            val name = binding.tvName.text.toString();
            val username = binding.tvUsername.text.toString();
            val email = binding.tvEmail.text.toString();
            val password = binding.tvPassword.text.toString();
            val user=UserData(name,username,email,password)
            binding.tvName.text?.clear()
            binding.tvUsername.text?.clear()
            binding.tvEmail.text?.clear()
            binding.tvPassword.text?.clear()
            val intent =Intent(this,LoginActivity::class.java)
            startActivity(intent)
            databse = FirebaseDatabase.getInstance().getReference("Users")
            databse.child(username).setValue(user).addOnSuccessListener {
                Toast.makeText(this, "User Registered", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show()
            }


        }

    }
}