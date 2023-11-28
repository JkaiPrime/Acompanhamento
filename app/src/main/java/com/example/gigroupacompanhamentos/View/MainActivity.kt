package com.example.gigroupacompanhamentos.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gigroupacompanhamentos.R
import com.example.gigroupacompanhamentos.RecyclerView.PessoaAdapter
import com.example.gigroupacompanhamentos.databinding.ActivityMainBinding



open class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var pessoaAdapter:PessoaAdapter

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.enviar -> {
            startActivity(Intent(this,EnviarListaEmail::class.java))
            true
        }
        /*R.id.ConfigEmail->{
            true
        }*/
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnFabAdd.setOnClickListener {
            startActivity(Intent(this, CadastroActivity::class.java))
        }
        pessoaAdapter = PessoaAdapter(this)
        binding.rcvPessoas.layoutManager = LinearLayoutManager(this)
        binding.rcvPessoas.adapter = pessoaAdapter

    }
    override fun onStart() {
        super.onStart()
        pessoaAdapter.updateAdapter()
    }
}