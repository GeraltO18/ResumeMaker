package com.resumemaker.editor.presentation.editorPage.preview

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import resumemaker.composeapp.generated.resources.Res
import resumemaker.composeapp.generated.resources.University_Student_Resume_Template_web1
import resumemaker.composeapp.generated.resources.standout_resume_template

@Composable
fun Previewer() {
    val images = listOf(
        Res.drawable.University_Student_Resume_Template_web1,
        Res.drawable.standout_resume_template
    ) // Add more images if needed

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(images) { imageRes ->
            Image(
                painter = painterResource(imageRes),
                contentDescription = "Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                contentScale = ContentScale.Fit
            )
        }
    }
}