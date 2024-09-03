package com.example.bbobabboba.main.itemrecyclerview

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bbobabboba.custom.CustomActivity
import com.example.bbobabboba.databinding.ItemListBinding
import com.example.bbobabboba.randomnumber.RandomNumberActivity

class ItemRecyclerAdapter(val list : MutableList<RandomData>) : RecyclerView.Adapter<ItemRecyclerAdapter.CustomViewHolder>(){
    // 커스텀뷰홀더에 binding값을 넘겨줌
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context))
        return CustomViewHolder(binding)
    }

    // 아이템 개수를 받아온 list값의 크기로 지정
    override fun getItemCount(): Int = list.size

    // 각 아이템을 뿌려줌
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val binding = holder.binding
        holder.binding.ivItemImg.setImageResource(list.get(position).img)
        holder.binding.tvItemName.text = list.get(position).itemname

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

    // 커스텀 뷰홀더
    class CustomViewHolder(val binding : ItemListBinding): RecyclerView.ViewHolder(binding.root){

    }
}