package browsers
import java.nio.file.Path

class Chrome:Chromium(){
    override val absPath: Path
    override val name: String = "Chrome"
    init{
        val envPath:String = System.getenv("LOCALAPPDATA")
        val chromePath:Path = Path.of("""$envPath\Google\Chrome\User Data""")
        this.absPath = chromePath
    }

    override suspend fun clean() {
        if(this.pathExists()){
            super.clean()
            this.chromeCleanUp()
            this.swReporter()
        }

    }
    /// Cleans other Google Services
    private fun chromeCleanUp(){
        val env:String = System.getenv("LOCALAPPDATA")
        val path = """$env\Google"""
        this.cleanUpTool(path)
        this.softwareReporter(path)
        this.toolbarCache(path)
    }
    /// Deletes Chrome CleanUp Tool Folder
    private fun cleanUpTool(path:String){
        this.delete("""$path\Chrome Cleanup Tool""")
    }
    /// Deletes Software Reporter Tool Folder
    private fun softwareReporter(path:String){
        this.delete("""$path\Software Reporter Tool""")
    }
    //Deletes the other Software Reporter Tool Folder
    private fun swReporter(){
        this.delete("""${this.absPath}\SwReporter""")
    }
    /// Deletes Toolbar Cache Folder
    private fun toolbarCache(path:String){
        this.delete("""$path\Toolbar Cache""")
    }

}