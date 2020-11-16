package manager
import Cleaner
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

abstract class CleanManager: Manager<Cleaner>() {
    abstract override val name: String
    abstract override val items: Array<Cleaner>
    override suspend fun startCleaning() = coroutineScope{
        launch {
            items.forEach { launch { it.clean() } }
        }
    }
}