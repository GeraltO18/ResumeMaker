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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.resumemaker.editor.presentation.editorPage.EditorViewModel
import sh.calvin.reorderable.ReorderableItem
import sh.calvin.reorderable.rememberReorderableLazyListState

@Composable
fun educationDropdown(
    viewModel: EditorViewModel
) {
    val isEdTabOpen by viewModel.isEdTabOpen.collectAsState()

    val defaultEducationList = listOf(
        "PSG College of Technology, BTech IT",
        "K.V.S. Matriculation Higher Secondary School, 12th standard",
        "K.V.S. English Medium School, 10th standard"
    )
    var educationItems by remember { mutableStateOf(defaultEducationList) }
    val listState = rememberLazyListState()
    val reorderableState = rememberReorderableLazyListState(listState) { from, to ->
        educationItems = educationItems.toMutableList().apply {
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
                .clickable { viewModel.toggleEdTab() }
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
                        text = "Education",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }
                Icon(
                    imageVector = if (isEdTabOpen) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = "Expand",
                    tint = Color.Black
                )
            }
        }

        // Expandable Content
        AnimatedVisibility(visible = isEdTabOpen) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(Color.White, RoundedCornerShape(12.dp))
            ) {
                LazyColumn(state = listState) {
                    itemsIndexed(educationItems, key = { _, item -> item }) { index, item ->
                        ReorderableItem(reorderableState, key = item) { _ ->
                            Column(
                                modifier = Modifier.semantics {
                                    customActions = listOf(
                                        CustomAccessibilityAction(
                                            label = "Move Up",
                                            action = {
                                                if (index > 0) {
                                                    educationItems = educationItems.toMutableList().apply {
                                                        add(index - 1, removeAt(index))
                                                    }
                                                    true
                                                } else false
                                            }
                                        ),
                                        CustomAccessibilityAction(
                                            label = "Move Down",
                                            action = {
                                                if (index < educationItems.size - 1) {
                                                    educationItems = educationItems.toMutableList().apply {
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
                                    Text(text = item, fontSize = 16.sp)
                                    Spacer(modifier = Modifier.weight(1f))
                                    Icon(
                                        modifier = Modifier.clickable { viewModel.toggleShowEdForm() },
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
                        .clickable { viewModel.toggleShowEdForm() }
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add",
                        tint = Color.Black
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Add Education", fontSize = 16.sp)
                }
            }
        }
    }
}

@Composable
fun eduForm(
    viewModel: EditorViewModel
){
    val qualification = remember { mutableStateOf("") }
    val collegeName = remember { mutableStateOf("School Name") }
    val year = remember { mutableStateOf("") }
    val minor = remember { mutableStateOf("") }
    val gpa = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
        ) {
            Column(
                modifier = Modifier.padding(15.dp)
            ){
                Text(collegeName.value, fontSize = 25.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                Spacer(modifier = Modifier.height(5.dp))
                OutlinedTextField(
                    value = qualification.value,
                    onValueChange = { qualification.value = it },
                    label = { Text("What is your degree/qualification?") },
                    placeholder = { Text("10th, 12th, BTech") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth(),
//                    isError = text.value.isNotEmpty() && !text.value.contains('@'),
                )
                Spacer(modifier = Modifier.height(5.dp))
                OutlinedTextField(
                    value = collegeName.value,
                    onValueChange = { collegeName.value = it },
                    label = { Text("Where did you earn your degree/qualification?") },
                    placeholder = { Text("PSG College of Technology") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth(),
//                    isError = text.value.isNotEmpty() && !text.value.contains('@'),
                )
                Spacer(modifier = Modifier.height(5.dp))
                OutlinedTextField(
                    value = year.value,
                    onValueChange = { year.value = it },
                    label = { Text("When did you earn it?") },
                    placeholder = { Text("2024") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth(),
//                    isError = text.value.isNotEmpty() && !text.value.contains('@'),
                )
                Spacer(modifier = Modifier.height(5.dp))
                OutlinedTextField(
                    value = minor.value,
                    onValueChange = { minor.value = it },
                    label = { Text("Did you minor in anything ?") },
                    placeholder = { Text("cs") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth(),
//                    isError = text.value.isNotEmpty() && !text.value.contains('@'),
                )
                Spacer(modifier = Modifier.height(5.dp))
                OutlinedTextField(
                    value = gpa.value,
                    onValueChange = { gpa.value = it },
                    label = { Text("GPA (if applicable)") },
                    placeholder = { Text("8.39") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth(),
//                    isError = text.value.isNotEmpty() && !text.value.contains('@'),
                )
                Spacer(modifier = Modifier.height(5.dp))
                OutlinedTextField(
                    value = description.value,
                    onValueChange = { description.value = it },
                    label = { Text("Open field for additional information") },
                    placeholder = { Text("summa") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth(),
//                    isError = text.value.isNotEmpty() && !text.value.contains('@'),
                )
                Spacer(modifier = Modifier.height(5.dp))
                Row {
                    Button(onClick = { viewModel.toggleShowEdForm() }) {
                        Text("Save")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = { viewModel.toggleShowEdForm() }) {
                        Text("Cancel")
                    }
                }
            }
        }
    }
}