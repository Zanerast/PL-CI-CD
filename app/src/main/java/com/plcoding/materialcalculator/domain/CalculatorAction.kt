package com.plcoding.materialcalculator.domain

sealed interface CalculatorAction {
    data class Number(val number: Int): CalculatorAction
    data class Op(val op: Operation): CalculatorAction
    object Clear: CalculatorAction
    object Calculate: CalculatorAction
    object Delete: CalculatorAction
    object Parentheses: CalculatorAction
    object Decimal: CalculatorAction
}
