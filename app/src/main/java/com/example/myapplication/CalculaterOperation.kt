package com.example.myapplication

sealed class CalculaterOperation(val symbol: String) {
    object Add: CalculaterOperation("+")
    object Subtract: CalculaterOperation("-")
    object Multiply: CalculaterOperation("*")
    object Divide: CalculaterOperation("/")
}