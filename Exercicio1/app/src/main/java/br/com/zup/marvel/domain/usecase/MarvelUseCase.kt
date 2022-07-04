package br.com.zup.marvel.domain.usecase

import android.app.Application
import br.com.zup.marvel.data.datasource.local.MarvelDatabase
import br.com.zup.marvel.domain.model.Marvel
import br.com.zup.marvel.domain.repository.MarvelRepository
import br.com.zup.marvel.ui.viewstate.ViewState

class MarvelUseCase(application: Application) {
    private val movieDao = MarvelDatabase.getDatabase(application).marvelDao()
    private val movieRepository = MarvelRepository(movieDao)

    suspend fun getAllMarvel(): ViewState<List<Marvel>> {
        return try {
            val marvel = movieRepository.getAllMarvel()
            ViewState.Success(marvel)
        } catch (ex: Exception) {
            ViewState.Error(Exception("Não foi possível carregar a lista de filmes da marvel!"))
        }
    }

    suspend fun insertMarvel(marvel: Marvel): ViewState<Marvel> {
        return try {
            movieRepository.insertMovie(marvel)
            ViewState.Success(marvel)
        } catch (ex: Exception) {
            ViewState.Error(Exception("Não foi possível cadastrar o filme da marvel!"))
        }
    }
}