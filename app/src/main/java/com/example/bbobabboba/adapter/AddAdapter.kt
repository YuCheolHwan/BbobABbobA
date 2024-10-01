package com.example.bbobabboba.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.bbobabboba.custom.CustomData
import com.example.bbobabboba.databinding.CustomBbobgiListBinding

class AddAdapter(private var list: MutableList<CustomData>) : RecyclerView.Adapter<AddAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val binding = CustomBbobgiListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.binding.btnClear.setOnClickListener {
            if (list.size > 2) {
                removeItem(position)
            } else {
                Toast.makeText(holder.binding.root.context, "최소 2가지 이상 입력하셔야 합니다.", Toast.LENGTH_SHORT).show()
            }
        }

        holder.binding.edtCustom.addTextChangedListener(CustomTextWatcher(position, list))
    }

    private fun removeItem(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, list.size)
    }

    class CustomViewHolder(val binding: CustomBbobgiListBinding) : RecyclerView.ViewHolder(binding.root)

    private class CustomTextWatcher(private val position: Int, private val list: MutableList<CustomData>) : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            list[position] = CustomData(s.toString())
        }

        override fun afterTextChanged(s: Editable?) {}
    }
}
