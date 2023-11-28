package com.example.gigroupacompanhamentos.ViewModel

import android.content.Context
import android.widget.Toast
import com.example.gigroupacompanhamentos.ROOM.Pessoa
import com.example.gigroupacompanhamentos.ROOM.PessoaDAO
import com.example.gigroupacompanhamentos.ROOM.PessoaDB
import java.lang.Exception

class CadastroViewModel(context: Context) {
    private val dao: PessoaDAO = PessoaDB.getInstance(context).getDao()
    private val context:Context = context
    fun cadastrarPessoa(pessoa: Pessoa){
        try {
            dao.salvar(pessoa)
            Toast.makeText(context, "Salvo Com Sucesso", Toast.LENGTH_SHORT).show()
        }catch (e:Exception){
            Toast.makeText(context, "Erro: $e", Toast.LENGTH_SHORT).show()
        }
    }
}