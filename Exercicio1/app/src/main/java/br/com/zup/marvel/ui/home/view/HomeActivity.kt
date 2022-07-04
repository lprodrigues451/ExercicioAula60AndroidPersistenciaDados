package br.com.zup.marvel.ui.home.view


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import br.com.zup.marvel.databinding.ActivityHomeBinding
import br.com.zup.marvel.domain.model.Marvel
import br.com.zup.marvel.ui.home.viewmodel.HomeViewModel
import br.com.zup.marvel.ui.marvelliist.view.MarvelListaActivity
import br.com.zup.marvel.ui.viewstate.ViewState


class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bAdicionar.setOnClickListener {
            val nome = binding.etNomeFillme.text.toString()
            val descricao =  binding.etInformaDetalhe.text.toString()

            if (nome.isNotEmpty()
                && descricao.isNotEmpty()){
                viewModel.insertMarvel(
                    Marvel(
                        nome = nome ,
                        descricao = descricao
                    )
                )
                limparCampoEdicao()
            } else {
                Toast.makeText(this, "campo vazio!", Toast.LENGTH_LONG)
                    .show()
            }
        }
        initObserver()

        binding.bVerFilme.setOnClickListener {
            startActivity(Intent(this, MarvelListaActivity::class.java))
        }
    }

    private fun initObserver() {
        viewModel.marvelAddState.observe(this) {
            when (it) {
                is ViewState.Success -> {
                    Toast.makeText(this, "Filme cadastrado com sucesso!", Toast.LENGTH_LONG)
                        .show()
                }
                is ViewState.Error -> {
                    Toast.makeText(this, "${it.throwable.message}", Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
    }

    private fun limparCampoEdicao() {
        binding.etNomeFillme.text.clear()
        binding.etInformaDetalhe.text.clear()

    }
}
