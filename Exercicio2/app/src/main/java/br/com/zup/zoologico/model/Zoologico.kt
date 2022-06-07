package br.com.zup.zoologico.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Zoologico(private var animal: String,
                private var descricao: String) : Parcelable {

    fun getAnimal() = this.animal
    fun getDescricao() = this.descricao
}