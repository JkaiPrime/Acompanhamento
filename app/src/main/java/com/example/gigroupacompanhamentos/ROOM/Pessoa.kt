package com.example.gigroupacompanhamentos.ROOM

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Pessoas")
data class Pessoa(
    @PrimaryKey(autoGenerate = true) var id:Int,
    var nome:String,
    var genero:String,
    var telefone:String,
    var email:String,
    var status:String,
    var empresa:String
)