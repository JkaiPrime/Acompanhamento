package com.example.gigroupacompanhamentos.RecyclerView

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gigroupacompanhamentos.R

class PessoaViewHolder(pessoaLayout: View): RecyclerView.ViewHolder(pessoaLayout) {
    var item:TextView = pessoaLayout.findViewById(R.id.ItemRCV)
}