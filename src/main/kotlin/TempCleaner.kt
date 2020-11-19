import java.nio.file.Path

abstract class TempCleaner:Cleaner() {
    abstract override val absPath: Path
    abstract val name:String
    abstract override suspend fun clean()
    open suspend fun tempClean(){
        if(this.pathExists()){
            this.tempFileDeletion()
        }
    }

    /// Deletes temporary files/directories
    private fun tempFileDeletion(){
        println("-----Removing Temp Files/Directories from $name-----")
        val fileReg:Array<Regex> = arrayOf(
                """(?:\.tmp$)|(?:\.log$)|(?:\.old$)|(?:\.temp$)|(?:\.lock$)|(?:\.dat$)|(?:\.crash)|(?:\.etl)|(?:desktop\.ini)""".toRegex(),
        )
        val dirReg:Array<Regex> = arrayOf(
            """[Cc]ache$""".toRegex(),
            """[Ll]ogs?$""".toRegex(),
            """[Rr]eports$""".toRegex()
        )
        this.recursiveFileDeleter(this.absPath,fileReg,dirReg)
        println("-----Completed!-----")
    }
}