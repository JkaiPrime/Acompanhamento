package com.example.gigroupacompanhamentos.ViewModel

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.gigroupacompanhamentos.ROOM.Pessoa
import com.example.gigroupacompanhamentos.ROOM.PessoaDAO
import com.example.gigroupacompanhamentos.ROOM.PessoaDB
import java.lang.Exception

class DetalhesViewModel(context: Context) {
    private val dao:PessoaDAO = PessoaDB.getInstance(context).getDao()
    private lateinit var pessoa:Pessoa
    fun BuscarUm(id:Int):Pessoa{
        try {
            pessoa = dao.getOne(id)
        }catch (e:Exception){
            Log.d("Erro",e.toString())
        }
        return pessoa
    }
}