package data
import Cleaner
import java.nio.file.Path

class CommonFilesX86:Cleaner() {
    override val absPath: Path
    init{
        val envPath:String = System.getenv("COMMONPROGRAMFILES(x86)")
        val commonPath:Path = Path.of(envPath)
        this.absPath = commonPath
    }

    override suspend fun clean() {
        if (this.pathExists()){
            this.windowsLive()
        }
    }
    /// Removes Windows Live cache
    private fun windowsLive(){
        val path = """${this.absPath}\Windows Live\.cache"""
        this.delete(path)
    }
}