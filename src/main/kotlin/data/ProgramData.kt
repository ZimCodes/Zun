package data
import TempCleaner
import java.nio.file.Path

class ProgramData:TempCleaner() {
    override val absPath: Path
    override val name:String = "ProgramData"
    init {
        val envPath:String = System.getenv("PROGRAMDATA")
        val programPath:Path = Path.of(envPath)
        this.absPath = programPath
    }

    override suspend fun clean() {

    }
}