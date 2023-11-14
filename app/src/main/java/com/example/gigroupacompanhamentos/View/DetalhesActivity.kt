package com.example.gigroupacompanhamentos.View

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.gigroupacompanhamentos.ROOM.Pessoa
import com.example.gigroupacompanhamentos.ROOM.PessoaDAO
import com.example.gigroupacompanhamentos.ROOM.PessoaDB
import com.example.gigroupacompanhamentos.databinding.ActivityDetalhesBinding

class DetalhesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetalhesBinding
    private var id:Int = 0
    private lateinit var pessoa: Pessoa
    private lateinit var dao:PessoaDAO
    private fun getInformationsAsString(pessoa: Pessoa):String{
        return "Nome:${pessoa.nome}\nGenero:${pessoa.genero}\nTelefone:${pessoa.telefone}\n" +
                "E-mail:${pessoa.email}\nStatus:${pessoa.status}\nEmpresa:${pessoa.empresa}"
    }
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
        binding.btnCopiar.setOnClickListener {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("label", getInformationsAsString(pessoa))
            if (clip==null){
                Toast.makeText(this, "Falha na copia", Toast.LENGTH_SHORT).show()
            }else {
                clipboard.setPrimaryClip(clip)
            }
        }

    }
}