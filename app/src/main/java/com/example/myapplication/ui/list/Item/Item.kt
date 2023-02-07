package com.example.myapplication.ui.list.Item

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.myapplication.data.model.ListItemData
import com.example.myapplication.ui.list.ListItem

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MyListItem(
    data: ListItem,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        elevation = 90.dp,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            GlideImage(
                modifier = Modifier
                    .fillMaxSize(0.2f)
                    .height(100.dp)
                    .padding(4.dp),
                model = data.image,
                contentDescription = null
            ) {
                it.fitCenter().centerCrop()
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Text(
                    text = data.name ?: "",
                    style = MaterialTheme.typography.h5
                )


                Text(
                    text = data.species ?: "",
                    style = MaterialTheme.typography.subtitle1.copy(Color.Gray)
                )

            }
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                text = data.status ?: "",
                style = MaterialTheme.typography.h6
            )
        }
    }
}