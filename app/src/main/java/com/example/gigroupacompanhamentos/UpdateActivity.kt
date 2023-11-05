package com.example.gigroupacompanhamentos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import com.example.gigroupacompanhamentos.ROOM.Pessoa
import com.example.gigroupacompanhamentos.ROOM.PessoaDAO
import com.example.gigroupacompanhamentos.ROOM.PessoaDB
import com.example.gigroupacompanhamentos.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateBinding
    private var id:Int = 0
    private lateinit var dao:PessoaDAO
    private lateinit var pessoa: Pessoa



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        id = intent.getIntExtra("id",0)
        dao = PessoaDB.getInstance(this).getDao()
        pessoa = dao.getOne(id)


        binding.updateNome.text = Editable.Factory.getInstance().newEditable(pessoa.nome)
        binding.updateTelefone.text = Editable.Factory.getInstance().newEditable(pessoa.telefone)
        binding.updateEmail.text = Editable.Factory.getInstance().newEditable(pessoa.email)
        binding.updateEmpresa.text = Editable.Factory.getInstance().newEditable(pessoa.empresa)

        binding.radioGroupGenero.setOnCheckedChangeListener { radioGroup, checkedId ->
            val generoSelecionado = when (checkedId) {
                R.id.btnM -> "Masculino"
                R.id.btnF -> "Feminino"
                else -> "Masculino"
            }
            pessoa.genero = generoSelecionado
        }
        binding.radioGroupStatus.setOnCheckedChangeListener { radioGroup, checkedId ->
            val statusSelecionado = when (checkedId) {
                R.id.rdBtnAbordado -> "Abordado"
                R.id.rdBtnEntrevista -> "Em Entrevista"
                R.id.rbBtnAprovado -> "Aprovado"
                R.id.rbBtnReprovado -> "Reprovado"
                R.id.rbBtnAdmissao -> "Em Admissão"
                else -> "Abordado" // Valor padrão se nenhum botão for selecionado
            }
            pessoa.status = statusSelecionado
        }




        binding.btnDel.setOnClickListener {
            dao.deletar(pessoa)
            finish()
        }
        binding.btnUpdate.setOnClickListener {
            pessoa.nome = binding.updateNome.text.toString()
            pessoa.telefone = binding.updateTelefone.text.toString()
            pessoa.email = binding.updateEmail.text.toString()
            pessoa.empresa = binding.updateEmpresa.text.toString()
            dao.atualizar(pessoa)
            finish()
        }

    }
}