import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.resumemaker.editor.presentation.ui.components.buttons.AppButton
import com.resumemaker.editor.presentation.ui.components.buttons.ButtonType
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() {
    MaterialTheme {
        Column(Modifier.fillMaxSize()) {
            Text("Resume Maker! ")
            AppButton(text = "Sample Button 1", buttonType = ButtonType.Primary, onClick = {})
            AppButton(text = "Sample Button 2", buttonType = ButtonType.Secondary, onClick = {})

        }
    }
}