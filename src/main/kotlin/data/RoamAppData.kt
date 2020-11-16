package data
import java.nio.file.Path

class RoamAppData:AppData(){
    override val absPath: Path
    override val name: String = "Roaming AppData"
    init{
        val envPath:String = System.getenv("APPDATA")
        val appPath: Path = Path.of(envPath)
        this.absPath = appPath
    }

    override suspend fun clean() {
        if(this.pathExists()){
            println("Xxx--Begin Cleaning Roaming AppData--xxX")
            this.recentShortcuts()
            println("Xxx--Roaming AppData Cleaning Ends--xxX")
        }
    }
    /// Roaming Recent Apps/Folders/Directories
    private fun recentShortcuts(){
        val path = """${this.absPath}\Roaming\Microsoft\Windows\Recent Items"""
        this.deleteDirStream(path)
    }
}