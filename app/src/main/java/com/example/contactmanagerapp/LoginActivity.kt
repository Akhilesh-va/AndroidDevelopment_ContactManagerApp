package com.example.contactmanagerapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.contactmanagerapp.databinding.ActivityLoginBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class LoginActivity : AppCompatActivity() {
    companion object{
        const val KEY="com.example.contactmanagerapp.KEY"
    }
    lateinit var database :DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        val btnLogIn = findViewById<Button>(R.id.btnLogIn)
        val tvUsernma=findViewById<TextView>(R.id.tvUsername)
        val tvDirectSignUp = findViewById<TextView>(R.id.tvDirectSignUp)
        tvDirectSignUp.setOnClickListener{
            val intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }
        btnLogIn.setOnClickListener {
            val uniqueId=tvUsernma.text.toString()


            if(uniqueId.isNotEmpty()){
                database=FirebaseDatabase.getInstance().getReference("Users")
                database.child(uniqueId).get().addOnSuccessListener {
                    if(it.exists()){
                        val intent = Intent(this,ContactActivity::class.java)
                        intent.putExtra(KEY,uniqueId)
                        startActivity(intent)
                    }
                    else{
                        Toast.makeText(this, "User doesn't Exist", Toast.LENGTH_SHORT).show()
                    }
                }.addOnFailureListener {
                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "Please Enter Username", Toast.LENGTH_SHORT).show()
            }



        }


    }
}