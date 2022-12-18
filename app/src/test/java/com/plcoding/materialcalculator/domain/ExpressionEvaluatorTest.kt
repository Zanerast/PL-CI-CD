package com.plcoding.materialcalculator.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ExpressionEvaluatorTest {
    
    private lateinit var expressionEvaluator: ExpressionEvaluator
    
    @Test
    fun `expression evaluated correctly`() {
        expressionEvaluator = ExpressionEvaluator(
            listOf(
                ExpressionPart.Number(4.0),
                ExpressionPart.Op(Operation.ADD),
                ExpressionPart.Number(5.0),
                ExpressionPart.Op(Operation.SUBTRACT),
                ExpressionPart.Number(3.0),
                ExpressionPart.Op(Operation.MULTIPLY),
                ExpressionPart.Number(5.0),
                ExpressionPart.Op(Operation.DIVIDE),
                ExpressionPart.Number(3.0),
            )
        )
    
        // Then
        assertThat(expressionEvaluator.evaluate())
            .isEqualTo(4)
    }
    
    @Test
    fun `expression with decimal evaluated correctly`() {
        expressionEvaluator = ExpressionEvaluator(
            listOf(
                ExpressionPart.Number(4.5),
                ExpressionPart.Op(Operation.ADD),
                ExpressionPart.Number(5.5),
                ExpressionPart.Op(Operation.SUBTRACT),
                ExpressionPart.Number(3.5),
                ExpressionPart.Op(Operation.MULTIPLY),
                ExpressionPart.Number(5.5),
                ExpressionPart.Op(Operation.DIVIDE),
                ExpressionPart.Number(3.5),
            )
        )
        
        // Then
        assertThat(expressionEvaluator.evaluate())
            .isEqualTo(4.5)
    }
    
    @Test
    fun `term divide evaluated correctly`() {
        expressionEvaluator = ExpressionEvaluator(
            listOf(
                ExpressionPart.Number(3.0),
                ExpressionPart.Op(Operation.DIVIDE),
                ExpressionPart.Number(3.0)
            )
        )
        
        // Then
        assertThat(expressionEvaluator.evaluate())
            .isEqualTo(1)
    }
    
    @Test
    fun `term multiplication evaluated correctly`() {
        expressionEvaluator = ExpressionEvaluator(
            listOf(
                ExpressionPart.Number(3.0),
                ExpressionPart.Op(Operation.MULTIPLY),
                ExpressionPart.Number(3.0)
            )
        )
        
        // Then
        assertThat(expressionEvaluator.evaluate())
            .isEqualTo(9)
    }
    
    @Test
    fun `term percentage evaluated correctly`() {
        expressionEvaluator = ExpressionEvaluator(
            listOf(
                ExpressionPart.Number(3.0),
                ExpressionPart.Op(Operation.PERCENT),
                ExpressionPart.Number(200.0)
            )
        )
        
        // Then
        assertThat(expressionEvaluator.evaluate())
            .isEqualTo(6)
    }
    
    @Test
    fun `factor positive evaluated correctly`() {
        // When
        expressionEvaluator = ExpressionEvaluator(
            listOf(
                ExpressionPart.Op(Operation.ADD),
                ExpressionPart.Number(3.0)
            )
        )
        
        // Then
        assertThat(expressionEvaluator.evaluate())
            .isEqualTo(3)
    }
    
    @Test
    fun `factor negative evaluated correctly`() {
        // When
        expressionEvaluator = ExpressionEvaluator(
            listOf(
                ExpressionPart.Op(Operation.SUBTRACT),
                ExpressionPart.Number(3.0)
            )
        )
        
        // Then
        assertThat(expressionEvaluator.evaluate())
            .isEqualTo(-3)
    }
    
    @Test
    fun `factor expression evaluated correctly`() {
        // When
        expressionEvaluator = ExpressionEvaluator(
            listOf(
                ExpressionPart.Parentheses(ParenthesesType.Opening),
                ExpressionPart.Number(3.0),
                ExpressionPart.Parentheses(ParenthesesType.Closing)
            )
        )
        
        // Then
        assertThat(expressionEvaluator.evaluate())
            .isEqualTo(3)
    }
    
    @Test
    fun `factor percentage evaluated correctly`() {
        // When
        expressionEvaluator = ExpressionEvaluator(
            listOf(
                ExpressionPart.Op(Operation.PERCENT),
                ExpressionPart.Number(3.0)
            )
        )
        
        // Then
        assertThat(expressionEvaluator.evaluate())
            .isEqualTo(3)
    }
}
