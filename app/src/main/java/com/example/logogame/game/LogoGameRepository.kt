package com.example.logogame.game

import android.util.Log
import com.example.logogame.api.RetrofitFactory
import com.example.logogame.data.LogoGameModel
import com.example.logogame.data.quizBank.RandomQuizProvider

class LogoGameRepository: LogoGameProvider {

    private val logoGameService = RetrofitFactory.getService()
    private val questionBank: RandomQuizProvider by lazy {
        RandomQuizProvider()
    }

    override suspend fun loadQuiz(){
        val result = logoGameService.getLogoQuiz()
        questionBank.populateQuestion(result.quiz) {
            questionBank.isUpdated.postValue(true)
        }
    }
}

interface LogoGameProvider {
    suspend fun loadQuiz()

}

sealed class Result {
    data class Success(val data: ArrayList<LogoGameModel>): Result()
    data class Error(val errorMessage: String): Result()
}