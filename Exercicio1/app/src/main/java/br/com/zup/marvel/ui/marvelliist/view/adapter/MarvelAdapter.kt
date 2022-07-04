package br.com.zup.marvel.ui.marvelliist.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.zup.marvel.databinding.MarvelItemBinding
import br.com.zup.marvel.domain.model.Marvel

class MarvelAdapter(
    private var marvelList: MutableList<Marvel>,
    private val clickMarvel: (marvel: Marvel) -> Unit
):
    RecyclerView.Adapter<MarvelAdapter.ViewHolder>(){

    class ViewHolder(val binding: MarvelItemBinding ) : RecyclerView.ViewHolder(binding.root){
        fun showMarvelInfo(marvel : Marvel) {
            binding.rvItemNomePersonagem.text = marvel.nome
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MarvelItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val marvel = marvelList[position]
        holder.showMarvelInfo(marvel)
        holder.binding.cvItemMarvel.setOnClickListener {
           clickMarvel(marvel)
        }
//        ATENÇÃO
//        holder.showMarvelInfo(marvel)
    }

    override fun getItemCount(): Int= marvelList.size

    fun updateMarvelList(novaListaMarvel: List<Marvel>){
        if (marvelList.size == 0){
            marvelList = novaListaMarvel as MutableList<Marvel>
        }else{
            marvelList.addAll(novaListaMarvel)
        }
        notifyDataSetChanged()
    }

}