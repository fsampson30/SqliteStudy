package com.sampson.sqlitestudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etAge: EditText
    private lateinit var switch: Switch
    private lateinit var btnViewAll: Button
    private lateinit var btnAdd: Button
    private lateinit var rvItems: RecyclerView
    private lateinit var person: Person
    private lateinit var adapter: PersonAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etName = findViewById(R.id.etName)
        etAge = findViewById(R.id.etAge)
        switch = findViewById(R.id.switchActiveCustomer)
        btnViewAll = findViewById(R.id.btnViewAll)
        btnAdd = findViewById(R.id.btnAdd)
        rvItems = findViewById(R.id.rvItems)

        adapter = PersonAdapter(mutableListOf())
        rvItems.adapter = adapter
        rvItems.layoutManager = LinearLayoutManager(this)


        btnViewAll.setOnClickListener {
            Toast.makeText(this, "View All", Toast.LENGTH_SHORT).show()
        }

        btnAdd.setOnClickListener {
            if (validateFields()){
                adapter.addPerson(captureFields())
                clearFields()
            } else {
                etName.error = getString(R.string.necessary_field)
                etAge.error = getString(R.string.necessary_field)

            }

        }

        rvItems.setOnClickListener { position ->
            adapter.removePerson(position.id)
            Toast.makeText(this, position.id.toString(), Toast.LENGTH_LONG).show()
        }

    }

    fun captureFields(): Person {
        person = Person(1, etName.text.toString(), etAge.text.toString().toInt(), switch.isChecked)
        return person
    }

    fun clearFields() {
        etName.text.clear()
        etAge.text.clear()
        switch.isChecked = false
        etName.requestFocus()
    }

    fun validateFields(): Boolean{
        return !(etName.text.isEmpty() || etAge.text.isEmpty())
    }
}