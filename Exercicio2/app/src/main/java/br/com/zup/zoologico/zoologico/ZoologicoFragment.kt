package br.com.zup.zoologico.zoologico

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.zup.zoologico.R
import br.com.zup.zoologico.adapter.ZoologicoAdapter
import br.com.zup.zoologico.databinding.FragmentZoologicoBinding
import br.com.zup.zoologico.model.Zoologico

class ZoologicoFragment : Fragment() {
    private lateinit var binding: FragmentZoologicoBinding

    private val adapter: ZoologicoAdapter by lazy{
        ZoologicoAdapter(arrayListOf(), this::irParaDetalheAnimal)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentZoologicoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        exibirRecyclerView()

        binding.bvAdicionar.setOnClickListener {
            adicionarItemListaAnimal()
        }
    }

    private fun exibirRecyclerView() {
        binding.rvListaAnimal.adapter = adapter
        binding.rvListaAnimal.layoutManager = LinearLayoutManager(context)
    }

    private fun adicionarItemListaAnimal() {
        val listaAnimal = mutableListOf<Zoologico>()
        val animalRecebido = recuperarDadosCampoEdicao()

        if (animalRecebido != null) {
            listaAnimal.add(animalRecebido)
            adapter.atualizarListaAnimal(listaAnimal)
            exibirRecyclerView()
        } else {
            exibirMensagemErro()
        }
    }

    private fun irParaDetalheAnimal(zoologico: Zoologico) {

        val bundle = bundleOf("ANIMAL" to zoologico)

        NavHostFragment.findNavController(this).navigate(
            R.id.action_zoologicoFragment_to_detalheFragment, bundle
        )
    }
    private fun recuperarDadosCampoEdicao(): Zoologico? {
        val animal = binding.etNomeAnimal.text.toString()
        val descricaoProduto = binding.etDescricaoAnimal.text.toString()

        if (animal.isNotEmpty() && descricaoProduto.isNotEmpty()) {
            limparCampoEdicao()
            return Zoologico(animal, descricaoProduto)
        }
        return null
    }

    private fun exibirMensagemErro() {
        binding.etNomeAnimal.error = "Por favor preencha o campo de nome"
        binding.etDescricaoAnimal.error = "Por favor preencha o campo de detalhe"
    }

    private fun limparCampoEdicao() {
        binding.etNomeAnimal.text.clear()
        binding.etDescricaoAnimal.text.clear()
    }


}