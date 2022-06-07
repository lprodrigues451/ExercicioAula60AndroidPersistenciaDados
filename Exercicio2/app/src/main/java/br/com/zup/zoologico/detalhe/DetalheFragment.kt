package br.com.zup.zoologico.detalhe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.zup.zoologico.R
import br.com.zup.zoologico.databinding.FragmentDetalheBinding
import br.com.zup.zoologico.model.Zoologico

class DetalheFragment : Fragment() {
    private lateinit var binding: FragmentDetalheBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetalheBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recuperarExibirDadosDoAnimal()
    }

    private fun recuperarExibirDadosDoAnimal() {
        val animal = arguments?.getParcelable<Zoologico>("ANIMAL")
        if (animal != null){
            binding.tvAnimal.text = animal.getAnimal()
            binding.tvDescricaoAnimal.text = animal.getDescricao()
        }
    }
}