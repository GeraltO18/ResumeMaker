package com.resumemaker.editor.presentation.editorPage.forms

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.resumemaker.editor.domain.BaseViewModel

@Composable
fun eduForm(
    viewModel: BaseViewModel
){
    val qualification = remember { mutableStateOf("") }
    val collegeName = remember { mutableStateOf("School Name") }
    val year = remember { mutableStateOf("") }
    val minor = remember { mutableStateOf("") }
    val gpa = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }

    val fields = listOf(
        Triple(qualification, "What is your degree/qualification?", "10th, 12th, BTech"),
        Triple(collegeName, "Where did you earn your degree/qualification?", "PSG College of Technology"),
        Triple(year, "When did you earn it?", "2024"),
        Triple(minor, "Did you minor in anything?", "cs"),
        Triple(gpa, "GPA (if applicable)", "8.39"),
        Triple(description, "Open field for additional information", "summa")
    )

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
            Column(modifier = Modifier.padding(15.dp)) {
                Text(collegeName.value, fontSize = 25.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                Spacer(modifier = Modifier.height(5.dp))

                fields.forEach { (state, label, placeholder) ->
                    OutlinedTextField(
                        value = state.value,
                        onValueChange = { state.value = it },
                        label = { Text(label) },
                        placeholder = { Text(placeholder) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                }

                Row {
                    Button(onClick = { viewModel.changeDisplayPage("Home") }) {
                        Text("Save")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = { viewModel.changeDisplayPage("Home") }) {
                        Text("Cancel")
                    }
                }
            }
        }
    }
}