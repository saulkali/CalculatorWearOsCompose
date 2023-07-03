package com.saul.calculadora.presentation.modules.moduleHistory.viewModel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.saul.calculadora.presentation.common.entities.OperationEntity

class HistoryViewModel(private val context:Context):ViewModel() {
    var listOperationEntity:List<OperationEntity> by mutableStateOf(emptyList())

    init {
        loadPreference()
    }

    fun loadPreference(){
        val sharedPreference = context.getSharedPreferences("general",Context.MODE_PRIVATE)
        val jsonListOperation = sharedPreference.getString("listOperations","")
        val type = object: TypeToken<List<OperationEntity>>() {}.type
        listOperationEntity = Gson().fromJson(jsonListOperation,type) ?: emptyList()
    }

    fun deletePreference(){
        val sharedPreferences = context.getSharedPreferences("general", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
        listOperationEntity = emptyList()
    }
}