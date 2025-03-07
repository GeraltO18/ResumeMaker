package com.resumemaker.editor.presentation.editorPage

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.CustomAccessibilityAction
import androidx.compose.ui.semantics.customActions
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import resumemaker.composeapp.generated.resources.Res
import resumemaker.composeapp.generated.resources.University_Student_Resume_Template_web1
import resumemaker.composeapp.generated.resources.standout_resume_template
import sh.calvin.reorderable.ReorderableItem
import sh.calvin.reorderable.rememberReorderableLazyListState
import androidx.lifecycle.ViewModel

@Composable
fun EditorPage(viewModel: EditorViewModel){

    val isEdTabOpen by viewModel.isEdTabOpen.collectAsState()

    Row(modifier = Modifier.fillMaxSize()) { // Main page
        Column(modifier = Modifier.weight(1f).fillMaxHeight().background(Color(0xFFFAF3DD))){ // Form
            Row{
                // NavBar
                Column(
                    modifier = Modifier
                        .weight(0.2f)
                        .fillMaxHeight()
                        .padding(20.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFFC8D5B9))
                ) {
                    navbarIcons()
                }

                // ContentList
                Column(Modifier.weight(0.8f).fillMaxHeight()) {
                    resumeName()
                    personalDetail()
                    educationDropdown(viewModel,isEdTabOpen)
                }
            }
        }
        Column(modifier = Modifier.weight(1f).fillMaxHeight()){ // previewer
            Column (
                modifier = Modifier.fillMaxSize().padding(8.dp),
            ) {
                Previewer()
            }
        }
    }
}

@Composable
fun navbarIcons(){
    Button(
        onClick = { /* TODO: Handle click */ },
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8FC0A9)),
        shape = MaterialTheme.shapes.large, // Rounded corners
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.Home,
            contentDescription = "Home",
            tint = Color.Black // Icon color
        )
    }
}

@Composable
fun resumeName(){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(Color(0xFFC8D5B9)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Row (
            modifier = Modifier.clickable{}
        ){
            Text(
                text = "Creator Name",
                color = Color.Black,
                modifier = Modifier
                    .padding(15.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 24.sp, // Increase this value to make the text larger
            )
            Icon(
                modifier = Modifier.align(alignment = Alignment.CenterVertically),
                imageVector = Icons.Filled.Edit,
                contentDescription = "Edit",
                tint = Color.Black// Icon color
            )
        }
        Button(
            onClick = { /* TODO: Handle click */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF68B0AB)),
            shape = MaterialTheme.shapes.large, // Rounded corners
            modifier = Modifier.padding(16.dp)){
            Text("Download", color = Color.Black)
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = "Edit",
                tint = Color.Black // Icon color
            )
        }
    }
}

@Composable
fun personalDetail(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth() // Matches the top panel width
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0xFFC8D5B9))
                .padding(16.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Start
        ) {
            Column(modifier = Modifier.fillMaxWidth().padding(5.dp)) {
                Text("Name", fontSize = 20.sp, color = Color.Black, modifier = Modifier.padding(5.dp))
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(5.dp)) {
                    Icon(
                        imageVector = Icons.Filled.Email,
                        contentDescription = "Email",
                        tint = Color(0xFF68B0AB),
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        "Email",
                        fontSize = 18.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(5.dp)) {
                    Icon(
                        imageVector = Icons.Filled.Phone,
                        contentDescription = "Phone",
                        tint = Color(0xFF68B0AB),
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        "Phone",
                        fontSize = 18.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }

        // Edit Icon at the top-right corner
        Icon(
            imageVector = Icons.Filled.Edit,
            contentDescription = "Edit",
            tint = Color.Black,
            modifier = Modifier
                .align(Alignment.TopEnd) // Moves it to the top-right
                .padding(24.dp) // Adjust padding as needed
                .size(24.dp)
        )
    }
}

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

@Composable
fun educationDropdown(
    viewModel: EditorViewModel,
    isEdTabOpen: Boolean
) {
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
                        .clickable { /* Add education logic */ }
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


