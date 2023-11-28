package com.example.gigroupacompanhamentos.View

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.gigroupacompanhamentos.R
import com.example.gigroupacompanhamentos.ROOM.Pessoa
import com.example.gigroupacompanhamentos.ROOM.PessoaDAO
import com.example.gigroupacompanhamentos.ROOM.PessoaDB
import com.example.gigroupacompanhamentos.ViewModel.DetalhesViewModel
import com.example.gigroupacompanhamentos.databinding.ActivityDetalhesBinding

class DetalhesActivity : MainActivity() {
    private lateinit var binding: ActivityDetalhesBinding
    private var id:Int = 0
    private lateinit var pessoa: Pessoa
    private val viewModel:DetalhesViewModel = DetalhesViewModel(this)
    private fun getInformationsAsString(pessoa: Pessoa):String{
        return "Nome:${pessoa.nome}\nGenero:${pessoa.genero}\nTelefone:${pessoa.telefone}\n" +
                "E-mail:${pessoa.email}\nStatus:${pessoa.status}\nEmpresa:${pessoa.empresa}"
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.show_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.CopiarShow -> {
            Copiar()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }
    private fun Copiar(){
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("label", getInformationsAsString(pessoa))
        if (clip==null){
            Toast.makeText(this, "Falha na copia", Toast.LENGTH_SHORT).show()
        }else {
            clipboard.setPrimaryClip(clip)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalhesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        id = intent.getIntExtra("id",0)
        pessoa = viewModel.BuscarUm(id)

        binding.edtNome.text = pessoa.nome
        binding.edtTelefone.text = pessoa.telefone
        binding.edtEmail.text = pessoa.email
        binding.edtGenero.text = pessoa.genero
        binding.edtStatus.text = pessoa.status
    }
}