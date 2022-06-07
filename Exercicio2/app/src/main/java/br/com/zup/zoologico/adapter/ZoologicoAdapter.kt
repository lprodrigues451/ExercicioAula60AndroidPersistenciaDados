package br.com.zup.zoologico.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.zup.zoologico.databinding.ZoologicoItemBinding
import br.com.zup.zoologico.model.Zoologico

class ZoologicoAdapter ( private var listaAnimal: MutableList<Zoologico>,
                         private val clickProduto: (zoologico: Zoologico) -> Unit
) : RecyclerView.Adapter<ZoologicoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ZoologicoAdapter.ViewHolder {
        val binding = ZoologicoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val animal = listaAnimal[position]
        holder.exibirInformacoesView(animal)
        holder.binding.cvItemLista.setOnClickListener {
            clickProduto(animal)
        }
    }

    override fun getItemCount(): Int =  listaAnimal.size

    fun atualizarListaAnimal(novaListaAnimal: MutableList<Zoologico>){
        if (listaAnimal.size == 0){
            listaAnimal = novaListaAnimal
        }else{
            listaAnimal.addAll(novaListaAnimal)
        }

        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ZoologicoItemBinding): RecyclerView.ViewHolder(binding.root){

        fun exibirInformacoesView(zoologico: Zoologico){
            binding.tvAnimal.text = zoologico.getAnimal()
        }
    }
}