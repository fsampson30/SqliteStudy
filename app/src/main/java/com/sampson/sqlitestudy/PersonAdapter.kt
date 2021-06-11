package com.sampson.sqlitestudy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PersonAdapter(
    private val persons : MutableList<Person>

) : RecyclerView.Adapter<PersonAdapter.PersonViewHolder>(){

    class PersonViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        return PersonViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.person_item, parent,false))
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val currentPerson = persons[position]
        val tvPerson = holder.itemView.findViewById<TextView>(R.id.tvPersonItem)
        tvPerson.text = currentPerson.showInformation()
    }

    override fun getItemCount() = persons.size

    fun addPerson(person: Person){
        persons.add(person)
        notifyItemInserted(persons.size-1)
    }

    fun removePerson(position: Int){
        persons.removeAt(position)
        notifyItemRemoved(position)
    }
}