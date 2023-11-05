package com.example.gigroupacompanhamentos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gigroupacompanhamentos.ROOM.Pessoa
import com.example.gigroupacompanhamentos.ROOM.PessoaDAO
import com.example.gigroupacompanhamentos.ROOM.PessoaDB
import com.example.gigroupacompanhamentos.databinding.ActivityDetalhesBinding

class DetalhesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetalhesBinding
    private var id:Int = 0
    private lateinit var pessoa: Pessoa
    private lateinit var dao:PessoaDAO
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalhesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dao = PessoaDB.getInstance(this).getDao()
        id = intent.getIntExtra("id",0)
        pessoa = dao.getOne(id)

        binding.edtNome.text = pessoa.nome
        binding.edtTelefone.text = pessoa.telefone
        binding.edtEmail.text = pessoa.email
        binding.edtGenero.text = pessoa.genero
        binding.edtStatus.text = pessoa.status
        binding.edtEmpresa.text = pessoa.empresa
        binding.btnVoltar.setOnClickListener {
            finish()
        }
    }
}