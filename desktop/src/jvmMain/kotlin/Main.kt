import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import io.kraftsman.common.clients.MopconClient
import io.kraftsman.desktop.ui.containers.AppContainer
import io.kraftsman.desktop.viewmodels.AppViewModel

fun main() = application {
    val viewModel = AppViewModel(MopconClient())

    Window(
        onCloseRequest = ::exitApplication,
        title = "MOPCON Demo App",
        state = WindowState(
            size = DpSize(500.dp, 800.dp),
            position = WindowPosition.Aligned(Alignment.Center)
        )
    ) {
        MaterialTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                AppContainer(viewModel)
            }
        }
    }
}
