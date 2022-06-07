package br.com.zup.marvel.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import br.com.zup.marvel.*
import br.com.zup.marvel.adapter.MarvelAdapter
import br.com.zup.marvel.databinding.ActivityMainBinding
import br.com.zup.marvel.detalhe.Detalhe
import br.com.zup.marvel.model.Marvel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val marvelAdapter : MarvelAdapter by lazy {
        MarvelAdapter(arrayListOf(), this::irParaDetalheMarvel)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        exibirRecyclerView()

    }
    private fun exibirRecyclerView() {
        adicionarItem()
        binding.rvListaPersonagemMarvel.adapter = marvelAdapter
        binding.rvListaPersonagemMarvel.layoutManager = StaggeredGridLayoutManager(2,
            StaggeredGridLayoutManager.VERTICAL)
    }
    private fun adicionarItem() {
        val pegarPersonagemMarvel = mutableListOf<Marvel>()

        pegarPersonagemMarvel.add(Marvel("Homem Aranha", DESCRICAO_HOMEM_ARANHA))
        pegarPersonagemMarvel.add(Marvel("Homem de ferro", DESCRICAO_HOMEM_DE_FERRO))
        pegarPersonagemMarvel.add(Marvel("Hulk", DESCRICAO_HULK))
        pegarPersonagemMarvel.add(Marvel("THOR", DESCRICAO_THOR))
        pegarPersonagemMarvel.add(Marvel("Capitão America", DESCRICAO_CAPITAO_AMERICA))
        pegarPersonagemMarvel.add(Marvel("Doutor Estralho", DESCRICAO_DOUTOR_ESTRALHO))
        pegarPersonagemMarvel.add(Marvel("Pantera negra", DESCRICAO_PANTERA_NEGRA))
        pegarPersonagemMarvel.add(Marvel("Thanos", DESCRICAO_THANOS))
        pegarPersonagemMarvel.add(Marvel("Deadpool", DESCRICAO_DEADPOOL))
        pegarPersonagemMarvel.add(Marvel("Magneto", DESCRICAO_MAGNETO))


        marvelAdapter.atualizarListaDePersonagemMArvel(pegarPersonagemMarvel)
    }

    private fun irParaDetalheMarvel(marvel: Marvel) {
        val intent = Intent(this, Detalhe::class.java).apply {
            putExtra(CHAVE_MARVEL, marvel)
        }
        startActivity(intent)
    }
}