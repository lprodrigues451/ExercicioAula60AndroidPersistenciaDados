package br.com.zup.marvel.ui.marvelliist.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.zup.marvel.*
import br.com.zup.marvel.databinding.ActivityMarvelListaBinding
import br.com.zup.marvel.domain.model.Marvel
import br.com.zup.marvel.ui.detail.view.DetalheActivity
import br.com.zup.marvel.ui.marvelliist.view.adapter.MarvelAdapter
import br.com.zup.marvel.ui.marvelliist.viewmodel.MarvelListViewModel
import br.com.zup.marvel.ui.viewstate.ViewState

class MarvelListaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMarvelListaBinding

    private val viewModel: MarvelListViewModel by lazy {
        ViewModelProvider(this)[MarvelListViewModel::class.java]
    }
    private val marvelAdapter : MarvelAdapter by lazy {
        MarvelAdapter(arrayListOf(), this::irParaDetalheMarvel)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMarvelListaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        exibirRecyclerView()
        initObserver()
        viewModel.getAllMarvel()

    }
    private fun initObserver() {
        viewModel.movieListState.observe(this) {

            when (it) {
                is ViewState.Success -> {
                    marvelAdapter.updateMarvelList(it.data)
                }
                is ViewState.Error -> {
                    Toast.makeText(
                        this,
                        "${it.throwable.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
                else -> {}
            }
        }
    }

    private fun exibirRecyclerView() {
        binding.rvMarvelList.adapter = marvelAdapter
        binding.rvMarvelList.layoutManager = LinearLayoutManager(this)
    }


    private fun irParaDetalheMarvel(marvel: Marvel) {
        val intent = Intent(this, DetalheActivity::class.java).apply {
            putExtra(CHAVE_MARVEL, marvel)
        }
        startActivity(intent)
    }
}