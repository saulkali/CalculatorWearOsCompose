package com.saul.calculadora.presentation.modules.moduleMain.viewModel

import android.content.Context
import android.content.Intent
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.saul.calculadora.presentation.common.entities.OperationEntity
import com.saul.calculadora.presentation.common.utils.ValidatorUtil

class MainViewModel(private val context: Context):ViewModel() {

    var operationEntity by mutableStateOf(OperationEntity())

    var listOperationEntity:List<OperationEntity> by mutableStateOf(emptyList())

    private lateinit var vibrator: Vibrator

    init {
        loadPreference()
    }

    fun addCharacter(character:String){
        validateOperation()
        val updatedResult = operationEntity.result + character
        operationEntity = operationEntity.copy(result = updatedResult)
        vibrate()
    }

    fun validateOperation(){
        operationEntity.colorResult = Color.White
        if(operationEntity.operation.isNotEmpty()){
            operationEntity.result = operationEntity.operation
            operationEntity.operation = ""
        }
    }

    fun deleteCharacter(){
        validateOperation()
        val updatedResult = operationEntity.result.dropLast(1)
        operationEntity = operationEntity.copy(result = updatedResult)
    }

    private fun vibrate() {
        val vibrationDuration = 10
        if (!::vibrator.isInitialized) {
            vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        }

        val vibrationEffect = VibrationEffect.createOneShot(vibrationDuration.toLong(), VibrationEffect.DEFAULT_AMPLITUDE)
        vibrator.vibrate(vibrationEffect)
    }

    fun getResult() {
        try {
            if (operationEntity.operation.isEmpty()) {
                if (ValidatorUtil.isValidArithmeticExpression(operationEntity.result)) {
                    operationEntity = operationEntity.copy(
                        operation = operationEntity.result,
                        colorResult = Color.Green,
                        result = "=${ValidatorUtil.evaluateArithmeticExpression(operationEntity.result)}"
                    )
                    listOperationEntity = listOperationEntity.plus(operationEntity)
                    savePreference()
                }
            }

        } catch (ex: Exception) {
            operationEntity = operationEntity.copy(
                operation = operationEntity.result,
                colorResult = Color.Red,
                result = "error"
            )
            Toast.makeText(context, ex.message, Toast.LENGTH_SHORT).show()
        }
    }

    fun savePreference(){
        val sharedPreference = context.getSharedPreferences("general",Context.MODE_PRIVATE)
        val editorSharedPreference = sharedPreference.edit()

        val jsonOperations = Gson().toJson(listOperationEntity)
        editorSharedPreference.putString("listOperations",jsonOperations)
        editorSharedPreference.apply()
    }

    fun loadPreference(){
        val sharedPreference = context.getSharedPreferences("general",Context.MODE_PRIVATE)
        val jsonListOperation = sharedPreference.getString("listOperations","")
        val type = object: TypeToken<List<OperationEntity>>() {}.type
        listOperationEntity = Gson().fromJson(jsonListOperation,type) ?: emptyList()
    }

    fun clear() {
        operationEntity = operationEntity.copy(operation = "", result = "", colorResult = Color.White)
    }
}