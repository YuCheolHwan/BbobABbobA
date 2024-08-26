package com.example.bbobabboba.custom

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.bbobabboba.databinding.CustomBbobgiListBinding

class AddAdapter(val list: MutableList<CustomData>) : RecyclerView.Adapter<AddAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val binding = CustomBbobgiListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.binding.btnClear.setOnClickListener {
            if (list.size <= 2) {
                Toast.makeText(holder.binding.root.context, "최소 2가지 이상 입력하셔야 합니다.", Toast.LENGTH_SHORT).show()
            } else {
                removeItem(position)
            }
        }
    }

    private fun removeItem(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, list.size) // 인덱스 갱신
    }

    class CustomViewHolder(val binding: CustomBbobgiListBinding) : RecyclerView.ViewHolder(binding.root)
}
