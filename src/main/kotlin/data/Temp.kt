package data
import Cleaner
import java.nio.file.Path

class Temp:Cleaner() {
    override val absPath: Path
    init {
        val envPath:String = System.getenv("TEMP")
        val tempPath:Path = Path.of(envPath)
        this.absPath = tempPath
    }

    override suspend fun clean() {
        if(this.pathExists()){
            println("Xxx--Begin Cleaning Temp--xxX")
            this.removeAll()
            println("Xxx--Temp Cleaning Ends--xxX")
        }

    }
    /// Removes all files in this directory
    private fun removeAll(){
        this.deleteDirStream(this.absPath.toString())
    }

}