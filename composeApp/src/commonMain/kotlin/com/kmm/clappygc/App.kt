package com.kmm.clappygc

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import clappygamelearning.composeapp.generated.resources.Res
import clappygamelearning.composeapp.generated.resources.background
import clappygamelearning.composeapp.generated.resources.compose_multiplatform
import com.kmm.clappygc.util.ChewyFontFamily

@Composable
@Preview
fun App() {

    Box(modifier = Modifier.fillMaxSize()){
        Image(
            painter = painterResource(Res.drawable.background),
            modifier = Modifier.fillMaxSize(),
            contentDescription = "Background",
            contentScale = ContentScale.Crop
        )
    }

    Row(
        modifier = Modifier.fillMaxWidth().padding(all = 48.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = "BEST : 0",
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.displaySmall.fontSize,
            fontFamily = ChewyFontFamily()
        )

        Text(
            text = "0",
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.displaySmall.fontSize,
            fontFamily = ChewyFontFamily()
        )


    }

}