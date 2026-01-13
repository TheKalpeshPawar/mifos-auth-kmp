package org.mifos.auth.kmp.sample.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.mifos.auth.kmp.sample.Res
import org.mifos.auth.kmp.sample.ic_icon_powered_logo


@Composable
fun MifosPoweredCard(
    modifier: Modifier = Modifier,
    text: String? = "Powered by",
    icon: DrawableResource? = Res.drawable.ic_icon_powered_logo,
    textColor: Color = Color(0xFF6750A4),
    iconColor: Color = Color(0xFF6750A4)
) {
    Box(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                text = text ?: "",
                style = TextStyle(
                    fontSize = 10.sp,
                    fontWeight = FontWeight(600),
                ),
                color = textColor,
            )
            Spacer(modifier = Modifier.width(4.dp))
            if (icon != null) {
                Image(
                    painter = painterResource(icon,),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(iconColor)
                )
            }
        }
    }
}