package br.com.zup.marvel.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "filmes_marvel")
data class Marvel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "cod_filme")
    var codMarvel: Long = 1,

    @ColumnInfo(name = "nome")
    var nome: String,

    @ColumnInfo(name = "descricao")
    var descricao: String
) : Parcelable