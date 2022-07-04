package br.com.zup.marvel.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.zup.marvel.domain.model.Marvel

@Dao
interface MarvelDAO {

    // "Order" ordem do alfabeto
    @Query("SELECT * FROM filmes_marvel ORDER BY nome ASC") //Ou DESC decrescente
    fun getAllMarvel(): List<Marvel>
//Replace substitui
    @Insert(onConflict = OnConflictStrategy.REPLACE)// Ou IGNORE para não adicionar filme iguais
    fun insertMarvel(marvel: Marvel)
}