package com.example.gigroupacompanhamentos.Utils

import com.example.gigroupacompanhamentos.ROOM.Pessoa

class ValidateInput() {
    fun init(pessoa: Pessoa):Boolean{
        return validarNome(pessoa)
    }
    fun validarNome(pessoa: Pessoa):Boolean{
        if(pessoa.nome.isNotEmpty()){
           return validarTelefone(pessoa)
        }
        return false
    }
    fun validarTelefone(pessoa: Pessoa):Boolean{
        if (pessoa.telefone.isNotEmpty()){
            return validarEmail(pessoa)
        }
        return false
    }
    fun validarEmail(pessoa: Pessoa):Boolean{
        if (pessoa.email.isNotEmpty()){
            return validarGenero(pessoa)
        }
        return false
    }
    fun validarGenero(pessoa: Pessoa):Boolean{
        if (pessoa.genero.isNotEmpty()){
            return validarStatus(pessoa)
        }
        return false
    }
    fun validarStatus(pessoa: Pessoa):Boolean{
        if (pessoa.status.isNotEmpty()){
            return validarEmpresa(pessoa)
        }
        return false
    }
    fun validarEmpresa(pessoa: Pessoa):Boolean{
        if (pessoa.empresa.isNotEmpty()){
            return true
        }
        return false
    }
}