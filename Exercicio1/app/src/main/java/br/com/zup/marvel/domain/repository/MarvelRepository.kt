package br.com.zup.marvel.domain.repository

import br.com.zup.marvel.data.datasource.local.dao.MarvelDAO
import br.com.zup.marvel.domain.model.Marvel

class MarvelRepository(private val marvelDAO: MarvelDAO) {

    suspend fun getAllMarvel(): List<Marvel> = marvelDAO.getAllMarvel()

    suspend fun insertMovie(marvel: Marvel){
        marvelDAO.insertMarvel(marvel)
    }

    suspend fun deleteAllList(){
        marvelDAO.deleteAllList()
    }
}