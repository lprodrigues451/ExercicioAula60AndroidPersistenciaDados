package br.com.zup.marvel.ui.home.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.zup.marvel.domain.model.Marvel
import br.com.zup.marvel.domain.usecase.MarvelUseCase
import br.com.zup.marvel.ui.viewstate.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val marvelUseCase = MarvelUseCase(application)
    val marvelAddState = MutableLiveData<ViewState<Marvel>>()

    fun insertMarvel(marvel: Marvel) {

        if (marvel.nome.isNotEmpty()
            && marvel.descricao.isNotEmpty()
        ) {
            viewModelScope.launch {
                try {
                    val response = withContext(Dispatchers.IO) {
                        marvelUseCase.insertMarvel(marvel)
                    }
                    marvelAddState.value = response
                } catch (ex: Exception) {
                    ex.printStackTrace()
                    marvelAddState.value =
                        ViewState.Error(Throwable("Não foi possível inserir o filme!"))
                }
            }
        } else {
            marvelAddState.value =
                ViewState.Error(Throwable("Algum campo vazio"))        }
    }

    fun deleteAllList(){
        viewModelScope.launch {
            val response = withContext(Dispatchers.IO){
                marvelUseCase.deleteAllList()
            }
        }
    }

}