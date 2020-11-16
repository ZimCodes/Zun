package browsers

import Cleaner
import java.nio.file.Path

class Firefox: Cleaner(){
    override val absPath:Path
    init{
        val envPath:String = System.getenv("APPDATA")
        val firefoxPath = """$envPath\Mozilla\Firefox\Profiles"""
        this.absPath = Path.of(firefoxPath)
    }
    /// Cleans Firefox Browser
    override suspend fun clean(){
        if(this.pathExists()){
            println("Xxx--Begin Cleaning FireFox--xxX")
            val profiles = this.dirStream(this.absPath.toString())
            for(profilePath in profiles){
                removeDataReports(profilePath)
            }
            this.removeJournals()
            this.crashReports()

            println("Xxx--Firefox Cleaning Ended--xxX")
        }

    }
    /// Deletes the archived directory [$profile\datareporting\archived]
    private fun removeDataReports(profile:Path){
        println("-----Cleaning Archives-----")
        val archivedReportDir = """$profile\datareporting\archived"""
        this.delete(archivedReportDir)
        println("-----Completed!-----")
    }
    private fun removeJournals(){
        println("-----Removing -journal files-----")
        val reg:Array<Regex> = arrayOf("""-journal$""".toRegex())
        this.recursiveFileDeleter(this.absPath,reg)
        println("-----Completed!-----")
    }
    /// Deletes the Crash Report folder
    private fun crashReports(){
        println("-----Cleaning Crash Reports folder-----")
        val env:String = System.getenv("APPDATA")
        val path = """$env\Mozilla\Firefox\Crash Reports"""
        this.delete(path)
        println("-----Completed!-----")
    }
}