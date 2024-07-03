package com.example.midtermproject.components.transportscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.midtermproject.ui.theme.Palette1
import com.example.midtermproject.ui.theme.Palette3

@Composable
fun LocationInputField(
    text: String,
    onTextChange: (String) -> Unit,
    placeholder: String,
    icon: ImageVector? = null,
    painter: Int? = null,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .background(Palette3, shape = MaterialTheme.shapes.small)
            .padding(horizontal = 10.dp, vertical = 12.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        icon?.let {
            Icon(
                imageVector = it,
                contentDescription = null,
                tint = Palette1,
                modifier = Modifier.size(24.dp)
            )
        }
        painter?.let {
            Icon(
                painter = painterResource(id = it),
                contentDescription = null,
                tint = Palette1,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        BasicTextField(
            value = text,
            onValueChange = onTextChange,
            textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
            modifier = Modifier.weight(1f)
        ) { innerTextField ->
            if (text.isEmpty()) {
                Text(
                    text = placeholder,
                    style = TextStyle(color = Color.Gray, fontSize = 18.sp)
                )
            }
            innerTextField()
        }
    }
}
