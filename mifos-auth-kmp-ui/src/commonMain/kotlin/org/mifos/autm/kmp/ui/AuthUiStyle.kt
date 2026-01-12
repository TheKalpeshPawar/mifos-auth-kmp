package org.mifos.autm.kmp.ui

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class AuthUiStyle(
    val fieldShape: Shape = RoundedCornerShape(12.dp),
    val buttonShape: Shape = RoundedCornerShape(50),

    val backgroundColor: Color = Color.White,

    val containerModifier: Modifier = Modifier.Companion,
    val fieldModifier: Modifier = Modifier.Companion,


    val textFieldBackgroundColor: Color = Color.White,
    val textFieldFocusedBackgroundColor: Color = Color.White,
    val textFieldBorderColor: Color = Color(0xFFDBE2F9),
    val textFieldFocusedBorderColor: Color = Color(0xFFDBE2F9),
    val textFieldTextColor: Color = Color(0xFF1A1B20),
    val textFieldPlaceholderColor: Color = Color(0xFF44474F),
    val textFieldIconColor: Color = Color(0xFF44474F),


    val buttonBackgroundColor: Color = Color(0xFF445E91),
    val buttonDisabledBackgroundColor: Color = Color(0xFF445E91).copy(alpha = 0.6f),
    val buttonTextColor: Color = Color.White,
    val buttonLoadingIndicatorColor: Color = Color.White,


    val textFieldTextStyle: TextStyle = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal
    ),
    val buttonTextStyle: TextStyle = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium
    ),

    // Dimensions
    val buttonHeight: Dp = 56.dp,
    val spacingBetweenFields: Dp = 16.dp,
    val spacingBeforeButton: Dp = 24.dp,
    val spacingAfterHeader: Dp = 32.dp
)