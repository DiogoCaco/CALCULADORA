package com.example.myapplication

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CalculatorViewModel: ViewModel() {
    var state by mutableStateOf(CalculaterState())
        private set

    fun onAction (action: CalculaterAction) {
        when (action) {
            is CalculaterAction.Number -> enterNumber(action.number)
            is CalculaterAction.Decimal -> enterDecimal()
            is CalculaterAction.Clear -> state = CalculaterState()
            is CalculaterAction.Operation -> enterOperation(action.operation)
            is CalculaterAction.Calculate -> enterCalculate()
            is CalculaterAction.Delete -> performDelete()

        }
    }

    private fun performDelete() {
        when {
            state.number2.isNotBlank() -> state = state.copy(
                number2 =  state.number2.dropLast(1)
            )
            state.operation !=null -> state = state.copy(
                operation = null
            )
            state.number1.isNotBlank() -> state = state.copy(
                number1 =  state.number1.dropLast(1)
            )
        }
    }

    private fun enterCalculate() {
        val number1 = state.number1.toDoubleOrNull()
        val number2 = state.number2.toDoubleOrNull()
        if (number1 != null && number2 != null){
            val result = when(state.operation){
                is CalculaterOperation.Add ->number1 + number2
                is CalculaterOperation.Subtract ->number1 - number2
                is CalculaterOperation.Divide ->number1 / number2
                is CalculaterOperation.Multiply ->number1 * number2
                null -> return
            }
            state = state.copy(
                number1 = result.toString().take(15),
                number2 = "",
                operation = null
            )
        }
    }

    private fun enterOperation(operation: CalculaterOperation) {
        if (state.number1.isNotBlank()) {
            state = state.copy(operation = operation)
        }
    }

    private fun enterDecimal() {
        if (state.operation == null && !state.number1.contains(".")
            && state.number1.isNotBlank()
        ){
            state = state.copy(
                number1 = state.number1 + "."
            )
            return
        }
        if (!state.number2.contains(".") && state.number2.isNotBlank()
        ){
            state = state.copy(
                number2 = state.number2 + "."
            )
        }
    }

    private fun enterNumber(number: Int) {
        if (state.operation == null) {
            if (state.number1.length >= MAX_NUM_LENGTH){
                return
            }
            state = state.copy(
                number1 = state.number1 + number
            )
            return
        }
        if (state.number2.length >= MAX_NUM_LENGTH) {
            return
        }
        state = state.copy(
            number2 = state.number2 + number
        )
    }
    companion object{
        private const val MAX_NUM_LENGTH = 8
    }
}