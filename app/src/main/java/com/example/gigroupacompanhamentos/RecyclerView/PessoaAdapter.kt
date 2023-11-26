package com.example.gigroupacompanhamentos.RecyclerView

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gigroupacompanhamentos.View.DetalhesActivity
import com.example.gigroupacompanhamentos.R
import com.example.gigroupacompanhamentos.ROOM.PessoaDB
import com.example.gigroupacompanhamentos.View.UpdateActivity

class PessoaAdapter(private val context: Context): RecyclerView.Adapter<PessoaViewHolder>() {
    private val dao = PessoaDB.getInstance(context).getDao()
    private var listaPessoas = dao.getAll()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PessoaViewHolder {
        val pessoasLayout = LayoutInflater.from(context)
            .inflate(R.layout.pessoa_layout,parent,false)
        return PessoaViewHolder(pessoasLayout)
    }

    override fun getItemCount(): Int {
        return listaPessoas.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PessoaViewHolder, position: Int) {
        val pessoa = listaPessoas[position]

        holder.item.text = "Nome: ${pessoa.nome}\nGenero: ${pessoa.genero}\n" +
                "Status: ${pessoa.status}\nEmpresa: ${pessoa.empresa}"

        holder.item.setOnClickListener {
            var intent = Intent(context, DetalhesActivity::class.java)
            intent.putExtra("id", pessoa.id)
            context.startActivity(intent)
            true
        }
        holder.item.setOnLongClickListener {
            var intent = Intent(context, UpdateActivity::class.java)
            intent.putExtra("id", pessoa.id)
            context.startActivity(intent)
            true
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    fun updateAdapter(){
        listaPessoas = emptyList()
        listaPessoas = dao.getAll()
        notifyDataSetChanged()
    }
}