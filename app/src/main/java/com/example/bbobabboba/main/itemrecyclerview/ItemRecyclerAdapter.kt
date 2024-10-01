package com.example.bbobabboba.main.itemrecyclerview

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bbobabboba.custom.CustomActivity
import com.example.bbobabboba.databinding.ItemListBinding
import com.example.bbobabboba.randomnumber.RandomNumberActivity

class ItemRecyclerAdapter(val list : MutableList<RandomData>) : RecyclerView.Adapter<ItemRecyclerAdapter.CustomViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context))
        return CustomViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val binding = holder.binding
        holder.binding.ivItemImg.setImageResource(list.get(position).img)
        holder.binding.tvItemName.text = list.get(position).itemName

        binding.root.setOnClickListener{
            var intent: Intent? = null
            when(position){
                0 ->{
                    intent = Intent(binding.root.context, CustomActivity::class.java)
                }
                1 ->{
                    intent = Intent(binding.root.context, RandomNumberActivity::class.java)
                }

            }

            binding.root.context.startActivity(intent)
        }
    }

    class CustomViewHolder(val binding : ItemListBinding): RecyclerView.ViewHolder(binding.root){

    }
}