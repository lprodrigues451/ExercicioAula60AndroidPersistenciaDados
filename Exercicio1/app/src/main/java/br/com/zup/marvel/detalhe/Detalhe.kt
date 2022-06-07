package br.com.zup.marvel.detalhe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import br.com.zup.marvel.CHAVE_MARVEL
import br.com.zup.marvel.R
import br.com.zup.marvel.databinding.ActivityDetalheBinding
import br.com.zup.marvel.databinding.ActivityMainBinding
import br.com.zup.marvel.model.Marvel

class Detalhe : AppCompatActivity() {
    private lateinit var binding: ActivityDetalheBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalheBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        recuperarExibirDadosMarvel()

    }
    private fun recuperarExibirDadosMarvel() {
        val marvel = intent.getParcelableExtra<Marvel>(CHAVE_MARVEL)
        if (marvel != null){
            exibirInformacoes(marvel)
        }
    }
    private fun exibirInformacoes(marvel: Marvel){
        binding.descricaoNomePersonagem.text = marvel.getNome()
        binding.descricaoSobrePersonagem.text = marvel.getDescricao()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            this.finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}