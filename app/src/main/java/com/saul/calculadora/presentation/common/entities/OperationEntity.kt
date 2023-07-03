package com.saul.calculadora.presentation.common.entities

import androidx.compose.ui.graphics.Color

data class OperationEntity(
    var operation:String = "",
    var result:String = "",
var colorResult:Color = Color.White)