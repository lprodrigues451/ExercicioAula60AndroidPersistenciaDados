package br.com.zup.marvel.data.datasource.local.dao

import androidx.room.*
import br.com.zup.marvel.domain.model.Marvel

@Dao
interface MarvelDAO {

    // "Order" ordem do alfabeto
    @Query("SELECT * FROM filmes_marvel ORDER BY nome ASC") //Ou DESC decrescente
    fun getAllMarvel(): List<Marvel>
//Replace substitui
    @Insert(onConflict = OnConflictStrategy.REPLACE)// Ou IGNORE para não adicionar filme iguais
    fun insertMarvel(marvel: Marvel)

    @Query("DELETE FROM filmes_marvel")
    fun deleteAllList()


}