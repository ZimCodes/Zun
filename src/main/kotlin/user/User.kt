package user
import Cleaner
import java.nio.file.Path

abstract class User:Cleaner() {
    override val absPath: Path
    protected abstract val name:String
    init{
        val envPath:String = System.getenv("USERPROFILE")
        val userPath:Path = Path.of(envPath)
        this.absPath = userPath
    }
    override suspend fun clean() {
        println("Xxx--Removing all content in $name Folder--xxX")
        this.removeAll()
        println("Xxx--Finish cleaning $name Folder--xxX")
    }
    /// Removes all pictures
    private fun removeAll(){
        this.deleteDirStream(this.absPath.toString(),"*")
    }
}