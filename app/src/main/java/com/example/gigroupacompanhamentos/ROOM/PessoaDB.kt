package com.example.gigroupacompanhamentos.ROOM

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Pessoa::class], version = 1)
abstract class PessoaDB:RoomDatabase() {
    abstract fun getDao():PessoaDAO
    companion object{
        private lateinit var instance:PessoaDB
        fun getInstance(context: Context):PessoaDB{
            if(!::instance.isInitialized){
                instance = Room.databaseBuilder(context,PessoaDB::class.java,
                    "personagem_db").allowMainThreadQueries().build()

            }
            return instance
        }
    }
}