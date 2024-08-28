package com.example.bbobabboba.custom

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.bbobabboba.databinding.CustomBbobgiListBinding

class AddAdapter(val list: MutableList<CustomData>) : RecyclerView.Adapter<AddAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val binding = CustomBbobgiListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: CustomViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.binding.btnClear.setOnClickListener {
            if (list.size <= 2) {
                Toast.makeText(holder.binding.root.context, "최소 2가지 이상 입력하셔야 합니다.", Toast.LENGTH_SHORT).show()
            } else {
                removeItem(position)
            }
        }
        // TextWatcher를 사용하여 EditText의 텍스트가 변경될 때마다 작업 실행
        holder.binding.edtCustom.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // 텍스트가 변경되기 전에 실행되는 코드
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // 텍스트가 변경될 때마다 실행되는 코드
                list[position] = CustomData(s.toString()) // list의 해당 항목 업데이트
            }

            override fun afterTextChanged(s: Editable?) {
                // 텍스트가 변경된 후에 실행되는 코드
            }
        })

    }

//    fun newList(){
//        for(i in 0 until list.size){
//            list[i] = CustomData(holder.binding.edtCustom.text.toString())
//        }
//    }
    private fun removeItem(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, list.size) // 인덱스 갱신
    }

    class CustomViewHolder(val binding: CustomBbobgiListBinding) : RecyclerView.ViewHolder(binding.root)
}
