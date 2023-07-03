/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package com.saul.calculadora.presentation.modules.moduleMain.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.saul.calculadora.R
import com.saul.calculadora.presentation.modules.moduleHistory.view.HistoryActivity
import com.saul.calculadora.presentation.modules.moduleHistory.viewModel.HistoryViewModel
import com.saul.calculadora.presentation.modules.moduleMain.viewModel.MainViewModel
import com.saul.calculadora.presentation.theme.CalculadoraTheme

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = MainViewModel(this)
        setContent {
            WearApp(viewModel)
        }
    }
}

@Composable
fun WearApp(viewModel: MainViewModel) {
    var operationEntity by remember { mutableStateOf(viewModel.operationEntity) }
    val context = LocalContext.current
    LaunchedEffect(viewModel.operationEntity) {
        operationEntity = viewModel.operationEntity
    }

    CalculadoraTheme {
        /* If you have enough items in your list, use [ScalingLazyColumn] which is an optimized
         * version of LazyColumn for wear devices with some added features. For more information,
         * see d.android.com/wear/compose.
         */
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f).padding(5.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "",
                    modifier = Modifier.weight(0.5f)
                )
                Text(
                    text = operationEntity.operation,
                    textAlign = TextAlign.Right,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "",
                    modifier = Modifier.weight(0.5f)
                )
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .weight(1f).padding(5.dp),
                horizontalArrangement = Arrangement.Center
            ){
                Image(
                    painter = painterResource(id = R.drawable.ic_history),
                    contentDescription = "Icon",
                    modifier = Modifier
                        .clickable {
                            val intent = Intent(context, HistoryActivity::class.java)
                            context.startActivity(intent)
                        }
                        .weight(0.5f)
                        .height(20.dp)
                )
                Text(
                    text = operationEntity.result,
                    color = operationEntity.colorResult,
                    textAlign = TextAlign.Right,
                    modifier = Modifier.weight(1f)
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_backspace),
                    contentDescription = "Icon",
                    modifier = Modifier
                        .clickable { viewModel.deleteCharacter() }
                        .weight(0.5f)
                        .height(20.dp)
                )
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(color = Color.DarkGray),
                horizontalArrangement = Arrangement.Center
            ){
                Button(
                    onClick = { viewModel.clear() },
                    modifier = Modifier
                        .weight(0.5f)
                        .height(20.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)) {
                    Text(text = "C", color = Color.Red)
                }
                Button(
                    onClick = {  },
                    modifier = Modifier
                        .weight(0.5f)
                        .height(20.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)) {
                    Text(text = "1/2", color = Color.Green)
                }
                Button(
                    onClick = { viewModel.addCharacter("/") },
                    modifier = Modifier
                        .weight(0.5f)
                        .height(20.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)) {
                    Text(text = "÷", color = Color.Green)
                }
                Button(
                    onClick = { viewModel.addCharacter("*") },
                    modifier = Modifier
                        .weight(0.5f)
                        .height(20.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)) {
                    Text(text = "x", color = Color.Green)
                }
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(color = Color.DarkGray),
                horizontalArrangement = Arrangement.Center
            ){
                Button(
                    onClick = { viewModel.addCharacter("7") },
                    modifier = Modifier
                        .weight(0.5f)
                        .height(20.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)) {
                    Text(text = "7", color = Color.White)
                }
                Button(
                    onClick = { viewModel.addCharacter("8") },
                    modifier = Modifier
                        .weight(0.5f)
                        .height(20.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)) {
                    Text(text = "8", color = Color.White)
                }
                Button(
                    onClick = { viewModel.addCharacter("9") },
                    modifier = Modifier
                        .weight(0.5f)
                        .height(20.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)) {
                    Text(text = "9", color = Color.White)
                }
                Button(
                    onClick = { viewModel.addCharacter("-") },
                    modifier = Modifier
                        .weight(0.5f)
                        .height(20.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)) {
                    Text(text = "-", color = Color.Green)
                }
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(color = Color.DarkGray),
                horizontalArrangement = Arrangement.Center
            ){
                Button(
                    onClick = { viewModel.addCharacter("4") },
                    modifier = Modifier
                        .weight(0.5f)
                        .height(20.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)) {
                    Text(text = "4", color = Color.White)
                }
                Button(
                    onClick = { viewModel.addCharacter("5") },
                    modifier = Modifier
                        .weight(0.5f)
                        .height(20.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)) {
                    Text(text = "5", color = Color.White)
                }
                Button(
                    onClick = { viewModel.addCharacter("6") },
                    modifier = Modifier
                        .weight(0.5f)
                        .height(20.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)) {
                    Text(text = "6",color = Color.White)
                }
                Button(
                    onClick = { viewModel.addCharacter("+") },
                    modifier = Modifier
                        .weight(0.5f)
                        .height(20.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)) {
                    Text(text = "+", color = Color.Green)
                }
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(color = Color.DarkGray),
                horizontalArrangement = Arrangement.Center
            ){
                Button(
                    onClick = { viewModel.addCharacter("1") },
                    modifier = Modifier
                        .weight(0.5f)
                        .height(20.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)) {
                    Text(text = "1", color = Color.White)
                }
                Button(
                    onClick = { viewModel.addCharacter("2") },
                    modifier = Modifier
                        .weight(0.5f)
                        .height(20.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)) {
                    Text(text = "2", color = Color.White)
                }
                Button(
                    onClick = { viewModel.addCharacter("3") },
                    modifier = Modifier
                        .weight(0.5f)
                        .height(20.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)) {
                    Text(text = "3", color = Color.White)
                }
                Button(
                    onClick = { viewModel.getResult() },
                    modifier = Modifier
                        .weight(0.5f)
                        .height(20.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green)) {
                    Text(text = "=", color = Color.White)
                }
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(color = Color.DarkGray),
                horizontalArrangement = Arrangement.Center
            ){
                Text(
                    text = "",
                    modifier = Modifier.weight(0.5f)
                )
                Button(
                    onClick = { viewModel.addCharacter("0") },
                    modifier = Modifier
                        .weight(0.5f)
                        .height(20.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)) {
                    Text(text = "0", color = Color.White)
                }

                Button(
                    onClick = { viewModel.addCharacter(".") },
                    modifier = Modifier
                        .weight(0.5f)
                        .height(20.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)) {
                    Text(text = ".", color = Color.White)
                }

                Text(
                    text = "",
                    modifier = Modifier.weight(0.5f)
                )
            }
        }
    }
}

@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun DefaultPreview() {
    val context = LocalContext.current
    val viewModel = MainViewModel(context)
    WearApp(viewModel)
}