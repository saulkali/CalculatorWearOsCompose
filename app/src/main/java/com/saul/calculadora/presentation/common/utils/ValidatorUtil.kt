package com.saul.calculadora.presentation.common.utils

import net.objecthunter.exp4j.ExpressionBuilder

object ValidatorUtil {

    fun isValidArithmeticExpression(expression: String): Boolean {
        try {
            ExpressionBuilder(expression).build()
            return true
        } catch (e: Exception) {
            return false
        }
    }

    fun evaluateArithmeticExpression(expression: String): String {
        val exp = ExpressionBuilder(expression).build()
        return exp.evaluate().toString()
    }
}