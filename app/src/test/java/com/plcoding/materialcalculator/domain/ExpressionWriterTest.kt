package com.plcoding.materialcalculator.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class ExpressionWriterTest {
    
    private lateinit var writer: ExpressionWriter
    
    @Before
    fun setup() {
        writer = ExpressionWriter()
    }
    
    @Test
    fun `initial parentheses parsed`() {
        writer.apply {
            processAction(CalculatorAction.Parentheses)
            processAction(CalculatorAction.Number(5))
            processAction(CalculatorAction.Op(Operation.ADD))
            processAction(CalculatorAction.Number(4))
            processAction(CalculatorAction.Parentheses)
        }
        assertThat(writer.expression)
            .isEqualTo("(5+4)")
    }
    
    @Test
    fun `closing parentheses at the start not parsed`() {
        writer.processAction(CalculatorAction.Parentheses)
        writer.processAction(CalculatorAction.Parentheses)
    
        assertThat(writer.expression)
            .isEqualTo("((")
    }
    
    @Test
    fun `parentheses around a number are parsed`() {
        writer.processAction(CalculatorAction.Parentheses)
        writer.processAction(CalculatorAction.Number(6))
        writer.processAction(CalculatorAction.Parentheses)
        
        assertThat(writer.expression)
            .isEqualTo("(6)")
    }
    
    @Test
    fun `operation cannot be entered twice in a row`(){
        writer.processAction(CalculatorAction.Number(6))
        writer.processAction(CalculatorAction.Op(Operation.MULTIPLY))
        writer.processAction(CalculatorAction.Op(Operation.MULTIPLY))
        writer.processAction(CalculatorAction.Number(6))
    
        assertThat(writer.expression)
            .isEqualTo("6x6")
    }
}