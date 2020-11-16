package data
import TempCleaner
import java.nio.file.Path

class Windows:TempCleaner() {
    override val absPath:Path
    override val name:String = "Windows"
    init{
        val winPath:Path = Path.of("""C:\Windows""")
        this.absPath = winPath
    }

    override suspend fun clean() {
        if(this.pathExists()){
            println("Xxx--Begin Cleaning Windows--xxX")
            this.tempFolder()
            this.softwareDistribution()
            println("Xxx--Windows Cleaning Ends--xxX")
        }
    }
    private fun tempFolder(){
        val path = """${this.absPath}\Temp"""
        this.deleteDirStream(path)
    }
    private fun softwareDistribution(){
        val path = """${this.absPath}\SoftwareDistribution"""
        this.dataStoreLogs(path)
        this.downloadBak(path)
    }
    private fun dataStoreLogs(path:String){
        val newPath = """$path\DataStore\Logs"""
        this.delete(newPath)
    }
    private fun downloadBak(path:String){
        val newPath = """$path\Download.bak"""
        this.delete(newPath)
    }

}