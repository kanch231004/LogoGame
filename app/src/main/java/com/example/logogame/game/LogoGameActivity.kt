package com.example.logogame.game

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.logogame.databinding.ActivityLogoMainBinding

class LogoGameActivity : ComponentActivity() {

    private lateinit var binding: ActivityLogoMainBinding
    private lateinit var viewModel: LogoGameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogoMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[LogoGameViewModel::class.java]
        loadQuestionBank()
        binding.btnSubmit.setOnClickListener {
            receiveInput()
        }
    }

    private fun loadQuestionBank() {
        viewModel.getQuestions()
        viewModel.isUpdated().observe(this) { updated ->
            if (updated) {
                displayQuestion()
            }
        }
    }

    private fun displayQuestion() {
        val question = viewModel.displayQuestion(0)
        binding.apply {
            Glide.with(this@LogoGameActivity).load(question?.imgURL).into(imageView) }

    }

    private fun receiveInput() {
        val answer = binding.etAnswer.text.toString()
        viewModel.validateAnswer(answer = answer)
    }
}
