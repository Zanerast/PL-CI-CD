package com.plcoding.materialcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.plcoding.materialcalculator.presentation.CalculatorScreen
import com.plcoding.materialcalculator.ui.theme.MaterialCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialCalculatorTheme {
                CalculatorScreen()
            }
        }
    }
}