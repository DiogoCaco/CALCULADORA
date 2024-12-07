package com.example.myapplication

sealed class CalculaterAction {
    data class Number(val number: Int) : CalculaterAction()
    object Clear : CalculaterAction()
    object Delete : CalculaterAction()
    object Decimal : CalculaterAction()
    object Calculate : CalculaterAction()
    data class Operation(val operation: CalculaterOperation) : CalculaterAction()
}
