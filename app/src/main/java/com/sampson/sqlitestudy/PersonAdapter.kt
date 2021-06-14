package com.sampson.sqlitestudy

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class PersonAdapter(
    private val persons : MutableList<Person>,

) : RecyclerView.Adapter<PersonAdapter.PersonViewHolder>(){

    class PersonViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        return PersonViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.person_item, parent,false))
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val currentPerson = persons[position]
        val tvPerson = holder.itemView.findViewById<TextView>(R.id.tvPersonItem)
        tvPerson.text = currentPerson.showInformation()

        holder.itemView.setOnClickListener{
            val sql = DataBaseHelper(holder.itemView.context)
            val result = sql.deleteOne(persons[position].id)
            Toast.makeText(holder.itemView.context, result, Toast.LENGTH_SHORT).show()
            notifyItemRemoved(position)
        }
    }

    override fun getItemCount() = persons.size

}