package com.example.alliase5

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.alliase5.Adapter.ViewHolder

class Adapter(private val data: ArrayList<data>, private val cont: home1):
    RecyclerView.Adapter<ViewHolder>() {


    class ViewHolder (private val view: View):RecyclerView.ViewHolder(view){
        val name:TextView = view.findViewById(R.id.name2)
        val father:TextView = view.findViewById(R.id.father)
        val mother :TextView = view.findViewById(R.id.mother2)
        val age:TextView = view.findViewById(R.id.age1)
        val gen:TextView=view.findViewById(R.id.gender2)
        val bio:TextView= view.findViewById(R.id.bio1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val layout = LayoutInflater.from(parent.context).inflate(R.layout.card,parent,false)
        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        val data = data[position]
        holder.name.text = data.name
        holder.mother.text = data.mother
        holder.father.text = data.father
        holder.age.text = data.age
        holder.bio.text = data.bio
        holder.gen.text=data.gender
    }
    override fun getItemCount(): Int {
        return data.size
    }

}
