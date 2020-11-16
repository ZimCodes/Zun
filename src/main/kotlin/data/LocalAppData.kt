package data
import java.nio.file.Path

class LocalAppData:AppData() {
    override val absPath: Path
    override val name: String = "Local AppData"
    init{
        val envPath:String = System.getenv("LOCALAPPDATA")
        val appPath: Path = Path.of(envPath)
        this.absPath = appPath
    }
}