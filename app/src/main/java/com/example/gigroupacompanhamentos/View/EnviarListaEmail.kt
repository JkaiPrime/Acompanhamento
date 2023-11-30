package com.example.gigroupacompanhamentos.View

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import com.example.gigroupacompanhamentos.R
import com.example.gigroupacompanhamentos.ROOM.Pessoa
import com.example.gigroupacompanhamentos.ROOM.PessoaDAO
import com.example.gigroupacompanhamentos.ROOM.PessoaDB
import com.example.gigroupacompanhamentos.databinding.ActivityEnviarListaEmailBinding
import java.lang.Exception

class EnviarListaEmail : AppCompatActivity() {

    private lateinit var binding: ActivityEnviarListaEmailBinding
    private lateinit var dao: PessoaDAO

    private fun sendList() {
        val message = formatarLista(dao.getAll())
        val target = binding.Target.text.toString()
        val recipiente = binding.Recipiente.text.toString()
        val mailIntent = Intent(Intent.ACTION_SEND)
        mailIntent.data = Uri.parse("mailto:")
        mailIntent.type = "text/plain"
        mailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipiente))
        mailIntent.putExtra(Intent.EXTRA_SUBJECT, target)
        mailIntent.putExtra(Intent.EXTRA_TEXT, message)

        try {
            startActivity(Intent.createChooser(mailIntent, "Send E-mail to: $target"))
        } catch (e: Exception) {
            Log.d("Erro na execução do envio", e.toString())
        }
    }

    private fun formatarLista(lista: List<Pessoa>): String {
        val mensagemFormatada = StringBuilder()
        try {
            for (item in lista) {
                mensagemFormatada.append(item).append("\n")
            }
            return mensagemFormatada.toString()
        } catch (e: Exception) {
            Log.d("Erro na formatação da Lista", e.toString())
        }
        return mensagemFormatada.toString()
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        val menuItemToHide = menu.findItem(R.id.enviar)
        menuItemToHide?.isVisible = false
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEnviarListaEmailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dao = PessoaDB.getInstance(this).getDao()
        binding.btnEnviar.setOnClickListener {
            sendList()
        }
    }
}
