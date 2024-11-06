package com.example.contactmanagerapp

import ContactUser
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ContactActivity : AppCompatActivity() {
    lateinit var database :DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_contact)
        val tvContactName=findViewById<TextView>(R.id.tvContactName)
        val tvContactEmail= findViewById<TextView>(R.id.tvContactEmail)
        val tvContactPhone=findViewById<TextView>(R.id.tvContactPhone)
        val btnAddContact=findViewById<Button>(R.id.btnAddContact)
        val tvWelcome=findViewById<TextView>(R.id.tvWelcome)
        val username=intent.getStringExtra(LoginActivity.KEY)
       tvWelcome.text="Welcome $username"
        btnAddContact.setOnClickListener {
            val name = tvContactName.text.toString()
            val email = tvContactEmail.text.toString()
            val phone = tvContactPhone.text.toString()
            val conatctUserData = ContactUser(name,email,phone)
            database= FirebaseDatabase.getInstance().getReference("Contacts")
            database.child("phone").setValue(conatctUserData).addOnSuccessListener {
                Toast.makeText(this, "Contact Added", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Contact Added")
            builder.setMessage("Name: $name \n Email: $email \n Phone: \n $phone")
            builder.setIcon(android.R.drawable.ic_lock_lock)
            builder.setPositiveButton("Done",{dialogInterfcae,which->
                Toast.makeText(this, "Be Happy", Toast.LENGTH_SHORT).show()
            })
            builder.setNegativeButton("Cancel",{dialogInterfcae,which->
                finish()
            })
            builder.show()
        }



    }
}