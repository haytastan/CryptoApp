package com.muhammed.mvvm_hilt_coinapp.ui.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.muhammed.mvvm_hilt_coinapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}