package com.example.authorizationtokeninretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.authorizationtokeninretrofit.databinding.ActivityMainBinding
import com.example.authorizationtokeninretrofit.ui.viewmodel.AuthViwModel
import com.example.authorizationtokeninretrofit.util.ApiState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val authViwModel: AuthViwModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getDetail()
    }

    private fun getDetail() {
        lifecycleScope.launchWhenStarted {
            authViwModel.data.collect {
                when (it) {
                    is ApiState.Success -> {
                        binding.apply {
                            progress.isVisible = false
                            text.isVisible = true

                            text.text = it.data.Success.toString()
                        }

                    }
                    is ApiState.Failure -> {
                        binding.apply {
                            progress.isVisible = false
                            text.isVisible = true

                            text.text = it.msg
                        }
                    }
                    ApiState.Loading -> {
                        binding.apply {
                            progress.isVisible = true
                            text.isVisible = false

                        }
                    }
                }
            }
        }
    }
}