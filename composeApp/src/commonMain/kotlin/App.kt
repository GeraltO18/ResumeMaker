import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import com.resumemaker.editor.presentation.editorPage.EditorPage
import com.resumemaker.editor.domain.BaseViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() {
    MaterialTheme {
        val viewModel = BaseViewModel()
        viewModel.init(listOf("Education","Work"))
        EditorPage(viewModel)
    }
}