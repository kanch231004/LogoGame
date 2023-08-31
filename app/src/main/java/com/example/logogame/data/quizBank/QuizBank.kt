package com.example.logogame.data.quizBank

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.logogame.data.LogoGameModel

class RandomQuizProvider : QuizBank {
    val isUpdated by lazy {
        MutableLiveData<Boolean>()
    }
    private var questions = ArrayList<LogoGameModel>()


    override fun getRandomQuestion(): LogoGameModel? {
        val randomIndex = (0..questions.size).random()

        if (!questions[randomIndex].isAsked) {
            questions[randomIndex].isAsked = true
            return  questions[randomIndex]
        } else {
            //retry
        }
        return  questions[randomIndex]
    }

    override fun getQuestion(index: Int): LogoGameModel? {
        return if (index > 0 && index < questions.size)
            questions[index]
        else {
            null
            //handle this case as required
        }

    }

    override fun populateQuestion(questions: ArrayList<LogoGameModel>, isUpdated : (Boolean) -> Unit) {
        questions.addAll(questions)
        isUpdated(true)
        Log.d("LogoGame", "populateQuestion: called")
    }

    override fun setUpdated(updated: Boolean) {
        isUpdated.postValue(updated)
    }

    override fun validateAnswer(question: LogoGameModel, ansProvided: String): Boolean {
        return question.name == ansProvided
    }
}

interface QuizBank {

    fun getRandomQuestion(): LogoGameModel?
    fun getQuestion(index: Int): LogoGameModel?
    fun populateQuestion(questions: ArrayList<LogoGameModel>, isUpdated : (Boolean) -> Unit)
    fun setUpdated(isUpdated: Boolean)
    fun validateAnswer(question: LogoGameModel, ansProvided: String): Boolean
}