package br.com.zup.marvel.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.zup.marvel.databinding.MarvelItemBinding
import br.com.zup.marvel.model.Marvel

class MarvelAdapter(
    private var listaMarvel: MutableList<Marvel>,
    private val clickProduto: (marvel: Marvel) -> Unit
):
    RecyclerView.Adapter<MarvelAdapter.ViewHolder>(){

    class ViewHolder(val binding: MarvelItemBinding ) : RecyclerView.ViewHolder(binding.root){
        fun adicionarInformacoesView(marvel : Marvel) {
            binding.rvItemNomePersonagem.text = marvel.getNome()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MarvelItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val marvel = listaMarvel[position]
        holder.adicionarInformacoesView(marvel)
        holder.binding.cvItemMarvel.setOnClickListener {
           clickProduto(marvel)
        }
    }

    override fun getItemCount(): Int= listaMarvel.size

    fun atualizarListaDePersonagemMArvel(novaListaMarvel: MutableList<Marvel>){
        if (listaMarvel.size == 0){
            listaMarvel = novaListaMarvel
        }else{
            listaMarvel.addAll(novaListaMarvel)
        }

        notifyDataSetChanged()
    }

}