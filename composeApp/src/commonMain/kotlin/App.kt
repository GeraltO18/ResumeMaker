import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import com.resumemaker.editor.presentation.editorPage.EditorPage
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() {
    MaterialTheme {
        EditorPage()
    }
}