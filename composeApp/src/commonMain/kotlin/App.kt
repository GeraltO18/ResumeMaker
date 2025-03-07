import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import com.resumemaker.editor.presentation.editorPage.EditorPage
import com.resumemaker.editor.presentation.editorPage.EditorViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() {
    MaterialTheme {
        val viewModel: EditorViewModel = EditorViewModel()
        EditorPage(viewModel)
    }
}