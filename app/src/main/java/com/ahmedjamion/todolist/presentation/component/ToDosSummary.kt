package com.ahmedjamion.todolist.presentation.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ToDosSummary(
    doneCount: Int,
    pendingCount: Int,
) {
    Row {
        ToDoSummaryItem("Done", doneCount)
        Spacer(Modifier.width(8.dp))
        ToDoSummaryItem("Pending", pendingCount)
    }
}

@Composable
fun ToDoSummaryItem(
    title: String,
    count: Int,
) {
    Card(
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
        ) {
            Text(title)
            Spacer(Modifier.width(8.dp))
            Text(
                text = "$count",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}