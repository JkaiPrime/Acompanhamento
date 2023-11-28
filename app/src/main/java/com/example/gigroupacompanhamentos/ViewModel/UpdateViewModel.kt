package com.example.gigroupacompanhamentos.ViewModel

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.gigroupacompanhamentos.ROOM.Pessoa
import com.example.gigroupacompanhamentos.ROOM.PessoaDAO
import com.example.gigroupacompanhamentos.ROOM.PessoaDB
import java.lang.Exception

class UpdateViewModel(context: Context) {
    private val dao: PessoaDAO = PessoaDB.getInstance(context).getDao()
    private lateinit var pessoa: Pessoa
    private val context:Context = context

    fun AtualizarPessoa(pessoa:Pessoa){
        try {
            dao.atualizar(pessoa)
        }catch (e:Exception){
            Log.d("Erro\n UpdateViewModel-> AtualizarPessoa",e.toString())
        }
    }
    fun BuscarUm(id:Int):Pessoa{
        try {
            pessoa = dao.getOne(id)
        }catch (e:Exception){
            Log.d("Erro\n" +
                    " UpdateViewModel-> BuscarUm",e.toString())
        }
        return pessoa
    }
    fun deletar(pessoa: Pessoa){
        try {
            dao.deletar(pessoa)
            Toast.makeText(context, "Deletado Com Sucesso", Toast.LENGTH_SHORT).show()
        }catch (e:Exception){
            Toast.makeText(context, "Erro ao Deletar\n$e", Toast.LENGTH_SHORT).show()
        }
    }
}