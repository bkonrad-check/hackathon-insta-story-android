package de.check24.hackathon.instagramstory.pages.story.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import coil.compose.rememberAsyncImagePainter
import de.check24.hackathon.instagramstory.mod.Banner
import de.check24.hackathon.instagramstory.mod.BannerType
import de.check24.hackathon.instagramstory.util.AnimatedBox
import de.check24.hackathon.instagramstory.util.advancedShadow
import de.check24.hackathon.instagramstory.util.colorFromHex
import de.check24.hackathon.instagramstory.util.pxToDp
import de.check24.hackathon.instagramstory.util.pxToSp
import kotlin.math.roundToInt

@Composable
fun BannersDrawer(
    list: List<Banner>,
    onInteractionClick: (String) -> Unit,
) {
    var screenSize by remember { mutableStateOf(Size.Zero) }
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Transparent)
            .onGloballyPositioned { coordinates ->
                screenSize = coordinates.size.toSize()
            },
    ) {
        list.forEach { banner ->
            banner.apply {
                val cornerRadiusDp = (screenSize.width * cornerRadius / 100)
                    .roundToInt()
                    .pxToDp()

                val shape = RoundedCornerShape(cornerRadiusDp)

                var boxModifier = Modifier
                    .wrapContentSize()
                    .offset {
                        IntOffset(
                            (screenSize.width * x / 100).roundToInt(),
                            (screenSize.height * y / 100).roundToInt(),
                        )
                    }
                    .rotate(rotation.toFloat())

                if (background != null) {
                    boxModifier = boxModifier
                        .advancedShadow(
                            color = Color.Black,
                            alpha = 0.99f,
                            cornersRadius = cornerRadiusDp,
                            shadowBlurRadius = 8.dp,
                            offsetY = 2.dp,
                            offsetX = 2.dp,
                        )
                        .clip(shape)
                        .background(background.colorFromHex(), shape)


                }
                if (actionDeeplink != null) {
                    boxModifier = boxModifier.clickable {
                        onInteractionClick(actionDeeplink)
                    }
                }
                AnimatedBox(
                    actionDeeplink != null,
                    boxModifier,
                ) {
                    var sizeModifier: Modifier = Modifier
                    if (width != null) {
                        sizeModifier = sizeModifier.width((screenSize.width * width / 100).roundToInt().pxToDp())
                    }
                    if (height != null) {
                        sizeModifier = sizeModifier.height((screenSize.height * height / 100).roundToInt().pxToDp())
                    }
                    sizeModifier = sizeModifier.padding(8.dp)
                    when (type) {
                        BannerType.TEXT -> Text(
                            text = text,
                            modifier = sizeModifier.padding(8.dp),
                            color = textColor.colorFromHex(),
                            fontSize = (screenSize.width * textSize / 100).roundToInt().pxToSp(),
                            textAlign = TextAlign.Center,
                        )
                        BannerType.IMAGE -> Image(
                            painter = rememberAsyncImagePainter(imageUrl),
                            contentDescription = "",
                            modifier = sizeModifier,
                        )
                    }
                }
            }
        }
    }
}
