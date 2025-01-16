package com.kmm.clappygc

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import clappygamelearning.composeapp.generated.resources.Res
import clappygamelearning.composeapp.generated.resources.background
import clappygamelearning.composeapp.generated.resources.bee_sprite
import clappygamelearning.composeapp.generated.resources.compose_multiplatform
import com.kmm.clappygc.domain.Game
import com.kmm.clappygc.domain.GameStatus
import com.kmm.clappygc.util.ChewyFontFamily
import com.stevdza_san.sprite.component.drawSpriteView
import com.stevdza_san.sprite.domain.SpriteSheet
import com.stevdza_san.sprite.domain.SpriteSpec
import com.stevdza_san.sprite.domain.rememberSpriteState

const val BEE_FRAME_SIZE = 80

@Composable
@Preview
fun App() {

    MaterialTheme {

        var screenWidth by remember { mutableStateOf(0) }
        var screenHeight by remember { mutableStateOf(0) }
        var game by remember {
            mutableStateOf(Game())
        }

        val spriteState = rememberSpriteState(
            totalFrames = 9,
            framesPerRow = 3
        )

        val spriteSpec = remember {
            SpriteSpec(
                screenWidth = screenWidth.toFloat(),
                default = SpriteSheet(
                    frameWidth = BEE_FRAME_SIZE,
                    frameHeight = BEE_FRAME_SIZE,
                    image = Res.drawable.bee_sprite
                )
            )
        }

        val currentFrame by spriteState.currentFrame.collectAsState()
        val sheetImage = spriteSpec.imageBitmap
        val animatedAngle by animateFloatAsState(
            targetValue = when {
                game.beeVelocity > game.beeMaxVelocity / 1.1 -> 30f
                else -> 0f
            }
        )



        LaunchedEffect(Unit) {
            game.start()
            spriteState.start()
        }

        LaunchedEffect(game.status) {
            while (game.status == GameStatus.Started) {
                withFrameMillis {
                    game.updateGameProgress()
                }
            }
            if (game.status == GameStatus.Over) {
                spriteState.stop()
            }

        }

        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(Res.drawable.background),
                modifier = Modifier.fillMaxSize(),
                contentDescription = "Background",
                contentScale = ContentScale.Crop
            )
        }

        Canvas(
            modifier = Modifier.fillMaxSize().onGloballyPositioned {
                val size = it.size
                if (screenWidth != size.width || screenHeight != size.height) {
                    screenWidth = size.width
                    screenHeight = size.height
                    game = game.copy(
                        screenWidth = screenWidth,
                        screenHeight = screenHeight
                    )
                }
            }.clickable {
                if (game.status == GameStatus.Started) {
                    game.jump()
                }
            }
        ) {
            /* drawCircle(
                 color = Color.Blue,
                 radius = game.bee.radius,
                 center = Offset(
                     x = game.bee.x,
                     y = game.bee.y
                 )
             )*/

            rotate(
                degrees = animatedAngle,
                pivot = Offset(
                    x = game.bee.x - game.beeRadius,
                    y = game.bee.y - game.beeRadius
                ),
            ) {

                drawSpriteView(
                    spriteState = spriteState,
                    spriteSpec = spriteSpec,
                    currentFrame = currentFrame,
                    image = sheetImage,
                    offset = IntOffset(
                        x = (game.bee.x.toInt() - game.beeRadius).toInt(),
                        y = (game.bee.y.toInt() - game.beeRadius).toInt()
                    )
                )
            }


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

        if (game.status == GameStatus.Over) {

            Column(
                modifier = Modifier.fillMaxSize().background(
                    color = Color.Black.copy(alpha = 0.5f)
                ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Game Over!",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.displaySmall.fontSize,
                    fontFamily = ChewyFontFamily()
                )

            }

        }

    }

}