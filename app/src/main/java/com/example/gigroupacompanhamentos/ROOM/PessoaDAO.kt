package com.example.gigroupacompanhamentos.ROOM

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PessoaDAO {
    @Insert
    fun salvar(pessoa: Pessoa):Long

    @Delete
    fun deletar(pessoa: Pessoa)

    @Update
    fun atualizar(pessoa: Pessoa)

    @Query("SELECT * FROM pessoas")
    fun getAll():List<Pessoa>

    @Query("SELECT * FROM pessoas WHERE id = :id")
    fun getOne(id:Int): Pessoa
}