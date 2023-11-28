package com.example.gigroupacompanhamentos.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import com.example.gigroupacompanhamentos.R
import com.example.gigroupacompanhamentos.ROOM.Pessoa
import com.example.gigroupacompanhamentos.ROOM.PessoaDAO
import com.example.gigroupacompanhamentos.ROOM.PessoaDB
import com.example.gigroupacompanhamentos.Utils.ValidateInput
import com.example.gigroupacompanhamentos.ViewModel.CadastroViewModel
import com.example.gigroupacompanhamentos.databinding.ActivityCadastroBinding

class CadastroActivity : MainActivity() {
    private lateinit var binding: ActivityCadastroBinding
    private val viewModel:CadastroViewModel = CadastroViewModel(this)
    private lateinit var ValidateInput:ValidateInput
    private lateinit var genero:String
    private lateinit var status:String

    private fun butãoGenero(binding: ActivityCadastroBinding):String{
        if (binding.btnLGBT.isChecked){return "LGBTQIAPN+"}
        if (binding.btnM.isChecked){return "Masculino"}
        if (binding.btnF.isChecked){return "Feminino"}
        return "Nada"
    }
    private fun botaoStatus(binding: ActivityCadastroBinding):String{
        if (binding.rdBtnAbordado.isChecked){return "Abordado"}
        if (binding.rdBtnEntrevista.isChecked){return "Em Entrevista"}
        if (binding.rbBtnAprovado.isChecked){return "Aprovado"}
        if (binding.rbBtnReprovado.isChecked){return "Reprovado"}
        if (binding.rbBtnAdmissao.isChecked){return "Em Admissão"}
        return "Nada"
    }
    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        val menuItemToHide = menu.findItem(R.id.enviar)
        menuItemToHide?.isVisible = false

        return super.onPrepareOptionsMenu(menu)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.btnCadastrar.setOnClickListener {
            ValidateInput = ValidateInput()
            var pessoa = Pessoa(0,
                binding.edtNome.text.toString(),
                genero = butãoGenero(binding),
                binding.edtTelefone.text.toString(),
                binding.edtEmail.text.toString(),
                status = botaoStatus(binding),
                binding.edtEmpresa.text.toString()
            )
            if (ValidateInput.init(pessoa)) {
                viewModel.cadastrarPessoa(pessoa)
                finish()
            }else{
                Toast.makeText(this, "Erro campos em branco", Toast.LENGTH_LONG).show()
            }
        }

    }

}