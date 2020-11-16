package manager
import Cleaner
import data.*

class DataCleaner:CleanManager() {
    override val name:String = "Data"
    override val items: Array<Cleaner> = arrayOf(
            LocalAppData(),
            LowAppData(),
            RoamAppData(),
            ProgramData(),
            SystemDrive(),
            Temp(),
            Windows(),
            CommonFilesX86(),
    )
}