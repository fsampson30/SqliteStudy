package com.sampson.sqlitestudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etAge: EditText
    private lateinit var switch: Switch
    private lateinit var btnViewAll: Button
    private lateinit var btnAdd: Button
    private lateinit var rvItems: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etName = findViewById(R.id.etName)
        etAge = findViewById(R.id.etAge)
        switch = findViewById(R.id.switchActiveCustomer)
        btnViewAll = findViewById(R.id.btnViewAll)
        btnAdd = findViewById(R.id.btnAdd)
        rvItems = findViewById(R.id.rvItens)

        btnViewAll.setOnClickListener {
            Toast.makeText(this, "View All", Toast.LENGTH_SHORT).show()
        }

        btnAdd.setOnClickListener {
            Toast.makeText(this, "Add", Toast.LENGTH_SHORT).show()
        }

    }
}