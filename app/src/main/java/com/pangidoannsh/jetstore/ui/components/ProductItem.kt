package com.pangidoannsh.jetstore.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pangidoannsh.jetstore.R
import com.pangidoannsh.jetstore.formatCurrency
import com.pangidoannsh.jetstore.ui.icons.rememberMoreHoriz

@Composable
fun ProductItem(
    title: String,
    image: Int,
    price: Long,
    city: String,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(6.dp),
        modifier = modifier,
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .fillMaxWidth()
                .height(188.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .background(Color.Transparent),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = title, color = Color(0xFF212121), fontSize = 14.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = formatCurrency(price), color = Color(0xFF212121),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            )
            Text(text = city, color = MaterialTheme.colorScheme.secondary, fontSize = 12.sp)
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                Icon(
                    imageVector = rememberMoreHoriz(), contentDescription = null,
                    tint = MaterialTheme.colorScheme.secondary
                )
            }
//            Row {
////                Icon(imageVector = rememberMoreHoriz(), contentDescription = null)
//                OutlinedButton(
//                    onClick = {},
//                    border = BorderStroke(1.dp, Color.DarkGray),
//                    shape = RoundedCornerShape(4.dp),
//                    contentPadding = PaddingValues(0.dp)
//                ) {
//                    Icon(imageVector = rememberMoreHoriz(), contentDescription = null)
//                }
//                Spacer(modifier = Modifier.width(4.dp))
//                OutlinedButton(
//                    onClick = {},
//                    shape = RoundedCornerShape(4.dp),
//                    border = BorderStroke(1.dp, Color.Green),
//                    colors = ButtonDefaults.outlinedButtonColors(
//                        containerColor = Color.Transparent,
//                        contentColor = Color.Green
//                    )
//                ) {
//                    Icon(
//                        imageVector = Icons.Default.Add,
//                        contentDescription = null,
//                        modifier = Modifier.size(12.dp)
//                    )
//                    Text(text = stringResource(id = R.string.cart))
//                }
//            }
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun ProductItemPreview() {
    ProductItem(
        title = "Name Product",
        image = R.drawable.keyboar1,
        price = 200_000,
        city = "Pekanbaru",
        modifier = Modifier.width(188.dp)
    )
}