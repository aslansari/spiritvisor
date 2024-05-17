import androidx.compose.runtime.Composable
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.setSingletonImageLoaderFactory
import com.aslansari.spiritvisor.SpiritVisorApp
import com.aslansari.spiritvisor.getAsyncImageLoader
import com.aslansari.spiritvisor.theme.SpiritVisorTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalCoilApi::class)
@Composable
@Preview
fun App() {
    SpiritVisorTheme {
        setSingletonImageLoaderFactory { context ->
            getAsyncImageLoader(context)
        }
        SpiritVisorApp()
    }
}
