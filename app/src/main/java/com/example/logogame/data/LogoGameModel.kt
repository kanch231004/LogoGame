package com.example.logogame.data

import com.google.gson.annotations.SerializedName

data class LogoGameModel(
    @SerializedName("imgURL") val imgURL: String, @SerializedName("name") val name: String, var isAsked:Boolean
)
data class LogoQuizResponse(@SerializedName("quiz") val quiz: ArrayList<LogoGameModel>)