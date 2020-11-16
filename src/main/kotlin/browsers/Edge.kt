package browsers

import java.nio.file.Path

class Edge:Chromium(){
    override val absPath: Path
    override val name:String = "Edge"
    init{
        val envPath:String = System.getenv("LOCALAPPDATA")
        val edgePath:Path = Path.of("""$envPath\Microsoft\Edge\User Data""")
        this.absPath = edgePath
    }
}