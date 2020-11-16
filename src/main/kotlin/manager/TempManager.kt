package manager

import TempCleaner
import data.*
import data.LowAppData
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class TempManager:Manager<TempCleaner>() {
    override val name: String = "Temporary Data"
    override val items: Array<TempCleaner> = arrayOf(
            LocalAppData(),
            LowAppData(),
            ProgramData(),
            RoamAppData(),
            Windows()
    )
    override suspend fun startCleaning() = coroutineScope{
        launch {
            items.forEach { launch { it.tempClean() } }
        }
    }
}