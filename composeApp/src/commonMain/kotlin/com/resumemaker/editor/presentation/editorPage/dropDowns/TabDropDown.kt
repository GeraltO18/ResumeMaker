package com.resumemaker.editor.presentation.editorPage.dropDowns

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.CustomAccessibilityAction
import androidx.compose.ui.semantics.customActions
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.resumemaker.editor.domain.BaseViewModel
import sh.calvin.reorderable.ReorderableItem
import sh.calvin.reorderable.rememberReorderableLazyListState

@Composable
fun tabDropDown(
    viewModel: BaseViewModel,
    title: String,
    data: List<String>
) {
    val isTabOpen by viewModel.getTabStateFlow(title).collectAsState()

    var listItems by remember { mutableStateOf(data) }
    val listState = rememberLazyListState()
    val reorderableState = rememberReorderableLazyListState(listState) { from, to ->
        listItems = listItems.toMutableList().apply {
            add(to.index, removeAt(from.index))
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Header Panel
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .clickable { viewModel.toggleTab(title) }
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "Education",
                        tint = Color.Black
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }
                Icon(
                    imageVector = if (isTabOpen) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = "Expand",
                    tint = Color.Black
                )
            }
        }

        // Expandable Content
        AnimatedVisibility(visible = isTabOpen) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(Color.White, RoundedCornerShape(12.dp))
            ) {
                LazyColumn(
                    state = listState,
                    modifier = Modifier.height(200.dp)
                ) {
                        itemsIndexed(listItems, key = { _, item -> item }) { index, item ->
                            ReorderableItem(reorderableState, key = item) { _ ->
                                Column(
                                    modifier = Modifier.semantics {
                                        customActions = listOf(
                                            CustomAccessibilityAction(
                                                label = "Move Up",
                                                action = {
                                                    if (index > 0) {
                                                        listItems = listItems.toMutableList().apply {
                                                            add(index - 1, removeAt(index))
                                                        }
                                                        true
                                                    } else false
                                                }
                                            ),
                                            CustomAccessibilityAction(
                                                label = "Move Down",
                                                action = {
                                                    if (index < listItems.size - 1) {
                                                        listItems = listItems.toMutableList().apply {
                                                            add(index + 1, removeAt(index))
                                                        }
                                                        true
                                                    } else false
                                                }
                                            )
                                        )
                                    }
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(12.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        IconButton(
                                            modifier = Modifier.draggableHandle(
                                                onDragStarted = { /* No-op for web */ },
                                                onDragStopped = { /* No-op for web */ }
                                            ),
                                            onClick = { }
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.MoreVert,
                                                contentDescription = "Reorder",
                                                tint = Color.Gray
                                            )
                                        }
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(text = item, fontSize = 16.sp, maxLines = 1, overflow = TextOverflow.Ellipsis, modifier = Modifier.weight(1f))
                                        Spacer(modifier = Modifier.weight(1f))
                                        Icon(
                                            modifier = Modifier.size(24.dp).clickable { viewModel.changeDisplayPage("EducationForm") },
                                            imageVector = Icons.Default.Edit,
                                            contentDescription = "Edit",
                                            tint = Color.Gray
                                        )
                                    }
                                    HorizontalDivider(color = Color.LightGray)
                                }
                            }
                        }
                    }
                // Add Education Button
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { viewModel.changeDisplayPage("EducationForm") }
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add",
                        tint = Color.Black
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Add $title", fontSize = 16.sp)
                }
            }
        }
    }
}