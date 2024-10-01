package com.example.bbobabboba.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bbobabboba.databinding.TableContentBinding
import com.example.bbobabboba.dbopenhelper.TableData

class HistoryAdapter(var list : MutableList<TableData>):RecyclerView.Adapter<HistoryAdapter.CustomViewHolder>(){


    fun updateList(newList: MutableList<TableData>) {
        list = newList
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val binding = TableContentBinding.inflate(LayoutInflater.from(parent.context))
        return CustomViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.binding.tvNo.text = list.get(position).no.toString()
        holder.binding.tvHistory.text = list.get(position).content
        
    }

    class CustomViewHolder(val binding : TableContentBinding):RecyclerView.ViewHolder(binding.root){
    }
}