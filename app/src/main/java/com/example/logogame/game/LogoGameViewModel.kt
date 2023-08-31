package com.example.logogame.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.logogame.data.LogoGameModel
import com.example.logogame.data.quizBank.RandomQuizProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LogoGameViewModel: ViewModel() {
    //should be injected with interface for unit testing
    private val repository = LogoGameRepository()
    private var selectedIndex: Int = 0
    private var selectedQuestion: LogoGameModel? = null
    private val questionBank: RandomQuizProvider by lazy {
        RandomQuizProvider()
    }

    fun getQuestions() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.loadQuiz()
            questionBank.setUpdated(true)
        }
    }

    fun displayQuestion(index: Int): LogoGameModel? {
        selectedQuestion = questionBank.getQuestion(index)
        return selectedQuestion
    }

    fun isUpdated() = questionBank.isUpdated

    fun validateAnswer(answer: String) {
        //ScoreKeeper class can keep the score of each correct answer
          selectedQuestion.let {
             if (it?.name.equals(answer)) {
                 displayQuestion(selectedIndex++)
             }
          }
    }

}