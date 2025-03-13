package com.resumemaker.editor.presentation.editorPage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.resumemaker.editor.domain.BaseViewModel
import com.resumemaker.editor.presentation.editorPage.dropDowns.tabDropDown
import com.resumemaker.editor.presentation.editorPage.forms.eduForm
import com.resumemaker.editor.presentation.editorPage.preview.Previewer

@Composable
fun EditorPage(viewModel: BaseViewModel){

    val displayPage = viewModel.displayPage.collectAsState()
    val defaultEducationList = listOf(
        "PSG College of Technology, BTech IT",
        "K.V.S. Matriculation Higher Secondary School, 12th standard",
        "K.V.S. English Medium School, 10th standard"
    )
    val defaultWorkList = listOf(
        "IBM pvt ltd",
        "Software AG",
        "Restogrow"
    )
    val defaultSkillList = listOf(
        "Frontend",
        "Backend",
        "Data"
    )
    val tabList = listOf("Education","Work","Skills")

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
                Column(
                    Modifier
                    .weight(0.8f)
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState())
                ) {
                    when(displayPage.value) {
                        "Home" -> {
                                resumeName()
                                personalDetail()
                                tabDropDown(viewModel,"Education",defaultEducationList)
                                tabDropDown(viewModel,"Work",defaultWorkList)
                                tabDropDown(viewModel,"Skills",defaultSkillList)
                        }
                        "EducationForm" -> {
                                eduForm(viewModel)
                        }
                    }
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