package com.example.bbobabboba.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bbobabboba.custom.CustomData
import com.example.bbobabboba.databinding.CustomListBinding

class CustomAdapter(var list : MutableList<CustomData>): RecyclerView.Adapter<CustomAdapter.CustomViewHolder>(){

    fun updateList(newList: MutableList<CustomData>) {
        list = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val binding = CustomListBinding.inflate(LayoutInflater.from(parent.context))
        return CustomViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.binding.tvItem.text = list.get(position).inputText
    }
    inner class CustomViewHolder(val binding : CustomListBinding) : RecyclerView.ViewHolder(binding.root)

}