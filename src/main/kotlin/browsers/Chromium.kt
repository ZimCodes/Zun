package browsers
import Cleaner
import java.nio.file.Path

open class Chromium:Cleaner(){
    override val absPath: Path = Path.of("")
    protected open val name:String = "Chromium"

    override suspend fun clean() {
        if(this.pathExists()){
            println("Xxx--Begin Cleaning $name--xxX")
            this.crashPadMetrics()
            this.browserMetrics()
            this.crashPad()
            this.shaderCache()
            this.defaultDir()
            println("Xxx--$name Cleaning Ended--xxX")
        }
    }

    protected open fun crashPadMetrics(){
        println("-----Cleaning Crashpad Metrics-----")
        val path = """${this.absPath}"""
        this.deleteDirStream(path,"""CrashpadMetrics*.pma""")
        println("-----Completed!-----")
    }
    protected open fun browserMetrics(){
        println("-----Cleaning Browser Metrics-----")
        this.delete("""${this.absPath}\BrowserMetrics""")
        this.deleteDirStream("""${this.absPath}""","""BrowserMetrics*.pma""")
        println("-----Completed!-----")
    }
    protected open fun crashPad(){
        println("-----Cleaning Crashpad-----")
        val metaFile = """${this.absPath}\Crashpad\metadata"""
        this.delete(metaFile)
        println("-----Completed!-----")
    }
    protected open fun shaderCache(){
        println("-----Cleaning Shader Cache-----")
        val grShader = """${this.absPath}\GrShaderCache"""
        this.delete(grShader)

        val shaderCache = """${this.absPath}\ShaderCache"""
        this.delete(shaderCache)
        println("-----Completed!-----")
    }
    /// Default Directory
    protected open fun defaultDir(){
        println("-----Cleaning Default Directory-----")
        val path = """${this.absPath}\Default"""

        this.multiDefaultFiles(path)
        this.singleDefaultFiles(path)
        this.entireDefaultDirs(path)

        println("-----Completed!-----")
    }
    protected open fun singleDefaultFiles(path:String){
        println("-----Cleaning Single Files from DefaultDir-----")
        //Bookmarks.bak
        this.delete("""$path\Bookmarks.bak""")
        //CURRENT
        this.delete("""$path\CURRENT""")
        //LOCK
        this.delete("""$path\LOCK""")
        //Network Persistent State
        this.delete("""$path\Network Persistent State""")
        //Visited Links
        this.delete("""$path\Visited Links""")

        println("-----Completed!-----")
    }
    protected open fun multiDefaultFiles(path:String,){
        println("-----Cleaning Mutiple Files from DefaultDir-----")
        this.deleteDirStream(path,"*.ldb")
        this.deleteDirStream(path,"Cookies*")
        this.deleteDirStream(path,"Extension Cookies*")
        this.deleteDirStream(path,"History*")
        this.deleteDirStream(path,"LOG*")
        this.deleteDirStream(path,"Login Data*")
        this.deleteDirStream(path,"MANIFEST*")
        this.deleteDirStream(path,"Network Action*")
        this.deleteDirStream(path,"QuotaManager*")
        this.deleteDirStream(path,"Reporting and NEL*")
        this.deleteDirStream(path,"Shortcuts*")
        this.deleteDirStream(path,"Top Sites*")
        this.deleteDirStream(path,"Web Data*")
        this.deleteDirStream(path,"Media History*")
        println("-----Completed!-----")
    }
    protected open fun entireDefaultDirs(path:String){
        println("-----Cleaning Directories in DefaultDir-----")
        this.delete("""$path\AutofillStrikeDatabase""")
        this.delete("""$path\Cache""")
        this.delete("""$path\Code Cache""")
        this.delete("""$path\data_reduction_proxy_leveldb""")
        this.delete("""$path\Extension Rules""")
        this.delete("""$path\Extension State""")
        this.delete("""$path\Feature Engagement Tracker""")
        this.delete("""$path\File System""")
        this.delete("""$path\GCM Store""")
        this.delete("""$path\GPUCache""")
        this.delete("""$path\IndexedDB""")
        this.delete("""$path\JumpListIconsMostVisited""")
        this.delete("""$path\JumpListIconsRecentClosed""")
        this.delete("""$path\Local Extension Settings""")
        this.delete("""$path\Local Storage""")
        this.delete("""$path\Managed Extension Settings""")
        this.delete("""$path\Pepper Data""")
        this.delete("""$path\Service Worker""")
        this.delete("""$path\Session Storage""")
        this.delete("""$path\Sessions""")
        this.delete("""$path\shared_proto_db""")
        this.delete("""$path\Site Characteristics Database""")
        this.delete("""$path\Storage""")
        this.delete("""$path\Sync Data""")
        this.delete("""$path\Sync Data Backup""")
        this.delete("""$path\Sync Extension Settings""")
        this.delete("""$path\VideoDecodeStats""")
        this.delete("""$path\Web Applications""")
        println("-----Completed!-----")
    }

}