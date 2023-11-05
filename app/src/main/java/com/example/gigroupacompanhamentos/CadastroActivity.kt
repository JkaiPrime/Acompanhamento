package com.example.gigroupacompanhamentos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import com.example.gigroupacompanhamentos.ROOM.Pessoa
import com.example.gigroupacompanhamentos.ROOM.PessoaDAO
import com.example.gigroupacompanhamentos.ROOM.PessoaDB
import com.example.gigroupacompanhamentos.Utils.ValidateInput
import com.example.gigroupacompanhamentos.databinding.ActivityCadastroBinding

class CadastroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCadastroBinding
    private lateinit var dao:PessoaDAO
    private lateinit var ValidateInput:ValidateInput
    fun validateRadioButtonGenero(view: View):String {
        if (view is RadioButton) {
            val checked = view.isChecked

            when (view.id) {
                R.id.btnM -> {
                    if (checked) {
                        return "Masculino"
                    }
                }
            }
        }
        return "Feminino"
    }
    fun validateRadioButtonStatus(view: View):String{
        if (view is RadioButton) {
            val checked = view.isChecked
            when(view.id){
                R.id.rdBtnEntrevista ->{
                    if (checked) {
                        return "Em Entrevista"
                    }
                }
                R.id.rbBtnAprovado ->{
                    if (checked) {
                        return "Aprovado"
                    }
                }
                R.id.rbBtnReprovado ->{
                    if (checked) {
                        return "Reprovado"
                    }
                }
                R.id.rbBtnAdmissao ->{
                    if (checked) {
                        return "Em Admissão"
                    }
                }
            }
        }
        return "Abordado"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dao = PessoaDB.getInstance(this).getDao()
        binding.btnCadastrar.setOnClickListener {
            val genero = validateRadioButtonGenero(binding.btnM)
            val status = validateRadioButtonStatus(binding.rdBtnEntrevista)
            var pessoa = Pessoa(0,
                binding.edtNome.text.toString(),
                genero,
                binding.edtTelefone.text.toString(),
                binding.edtEmail.text.toString(),
                status,
                binding.edtEmpresa.text.toString()
                )
            ValidateInput = ValidateInput()
            if (ValidateInput.init(pessoa)) {
                dao.salvar(pessoa)
                finish()
            }else{
                Toast.makeText(this, "Erro campos em branco", Toast.LENGTH_LONG).show()

            }
        }

    }
}