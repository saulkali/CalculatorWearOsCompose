/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package com.saul.calculadora.presentation.modules.moduleHistory.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.*
import com.saul.calculadora.presentation.modules.moduleHistory.viewModel.HistoryViewModel
import com.saul.calculadora.presentation.theme.CalculadoraTheme

class HistoryActivity : ComponentActivity() {
    private lateinit var viewModel:HistoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = HistoryViewModel(this)
        setContent {
            WearApp(viewModel)
        }
    }
}

@Composable
fun WearApp(viewModel: HistoryViewModel) {
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
            LazyColumn (modifier = Modifier
                .fillMaxHeight(0.7f)
                .fillMaxWidth(0.8f)){

                items(viewModel.listOperationEntity) { operationEntity ->
                    Row(
                        modifier =Modifier.fillMaxWidth().padding(4.dp)
                    ) {
                        Text(
                            text = operationEntity.operation,
                            textAlign = TextAlign.Right,
                        modifier = Modifier.fillMaxWidth(1f))
                    }
                    Row(
                        modifier =Modifier.fillMaxWidth().padding(4.dp)
                    ) {
                        Text(
                            text = operationEntity.result,
                            textAlign = TextAlign.Right,
                            color = Color.Green,
                            modifier = Modifier.fillMaxWidth(1f))
                    }
                }
            }
            Button(onClick = { viewModel.deletePreference() },
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .height(30.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)) {
                Text(text = "Borrar",color = Color.White)
            }
        }
    }
}

@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun DefaultPreview2() {
    val context = LocalContext.current
    val viewModel = HistoryViewModel(context)
    WearApp(viewModel)
}