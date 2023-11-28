package com.example.gigroupacompanhamentos.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.Menu
import android.view.MenuItem
import android.widget.Switch
import com.example.gigroupacompanhamentos.R
import com.example.gigroupacompanhamentos.ROOM.Pessoa
import com.example.gigroupacompanhamentos.ROOM.PessoaDAO
import com.example.gigroupacompanhamentos.ROOM.PessoaDB
import com.example.gigroupacompanhamentos.ViewModel.UpdateViewModel
import com.example.gigroupacompanhamentos.databinding.ActivityUpdateBinding

class UpdateActivity : MainActivity() {
    private lateinit var binding: ActivityUpdateBinding
    private var id:Int = 0
    private lateinit var pessoa: Pessoa
    private val viewModel = UpdateViewModel(this)
    private lateinit var genero:String
    private lateinit var status:String
    private fun Genero(binding: ActivityUpdateBinding):String{
        if (binding.btnUpdateOutroGenero.isChecked){return "Outro"}
        if (binding.btnUpdateM.isChecked){return "Masculino"}
        if (binding.btnUpdateF.isChecked){return "Feminino"}
        return "Nada"
    }
    private fun CheckRadioButtonGenero(binding: ActivityUpdateBinding, pessoa: Pessoa){
        if (pessoa.genero == "Masculino"){binding.btnUpdateM.isChecked=true}
        if (pessoa.genero == "Feminino"){binding.btnUpdateF.isChecked=true}
        if (pessoa.genero == "Outro"){binding.btnUpdateOutroGenero.isChecked=true}
    }
    private fun CheckRadioButtonStatus(binding: ActivityUpdateBinding, pessoa: Pessoa){
        if (pessoa.status == "Abordado"){binding.rdBtnUpdateAbordado.isChecked=true}
        if (pessoa.status == "Em Entrevista"){binding.rdBtnUpdateEntrevista.isChecked=true}
        if (pessoa.status == "Aprovado"){binding.rbBtnUpdateAprovado.isChecked=true}
        if (pessoa.status == "Reprovado"){binding.rbBtnUpdateReprovado.isChecked=true}
        if (pessoa.status == "Em Admissão"){binding.rbBtnUpdateAdmissao.isChecked=true}
    }
    private fun Status(binding: ActivityUpdateBinding):String{
        if (binding.rdBtnUpdateAbordado.isChecked){return "Abordado"}
        if (binding.rdBtnUpdateEntrevista.isChecked){return "Em Entrevista"}
        if (binding.rbBtnUpdateAprovado.isChecked){return "Aprovado"}
        if (binding.rbBtnUpdateReprovado.isChecked){return "Reprovado"}
        if (binding.rbBtnUpdateAdmissao.isChecked){return "Em Admissão"}
        return "Nada"
    }
    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        val menuItemToHide = menu.findItem(R.id.enviar)
        menuItemToHide?.isVisible = false

        return super.onPrepareOptionsMenu(menu)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        id = intent.getIntExtra("id",0)
        pessoa = viewModel.BuscarUm(id)
        CheckRadioButtonGenero(binding,pessoa)
        CheckRadioButtonStatus(binding,pessoa)

        binding.updateNome.text = Editable.Factory.getInstance().newEditable(pessoa.nome)
        binding.updateTelefone.text = Editable.Factory.getInstance().newEditable(pessoa.telefone)
        binding.updateEmail.text = Editable.Factory.getInstance().newEditable(pessoa.email)
        binding.updateEmpresa.text = Editable.Factory.getInstance().newEditable(pessoa.empresa)

        binding.btnDel.setOnClickListener {
            viewModel.deletar(pessoa)
            finish()
        }
        binding.btnUpdate.setOnClickListener {
            pessoa.nome = binding.updateNome.text.toString()
            pessoa.telefone = binding.updateTelefone.text.toString()
            pessoa.email = binding.updateEmail.text.toString()
            pessoa.empresa = binding.updateEmpresa.text.toString()
            pessoa.genero = Genero(binding)
            pessoa.status = Status(binding)
            viewModel.AtualizarPessoa(pessoa)
            finish()
        }

    }
}