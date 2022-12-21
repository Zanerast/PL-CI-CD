package com.plcoding.materialcalculator.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.plcoding.materialcalculator.domain.CalculatorAction
import com.plcoding.materialcalculator.domain.ExpressionWriter

class CalculatorViewModel(
    private val expressionWriter: ExpressionWriter = ExpressionWriter()
): ViewModel() {
    
    var expression by mutableStateOf("0")
        private set

    fun onAction(action: CalculatorAction) {
        expressionWriter.processAction(action)
        this.expression = expressionWriter.expression
    }
    
}
