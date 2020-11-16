package browsers

import java.nio.file.Path

class Brave:Chromium(){
    override val absPath:Path
    override val name:String = "Brave"
    init{
        val envPath:String = System.getenv("LOCALAPPDATA")
        val bravePath:Path = Path.of("""$envPath\BraveSoftware\Brave-Browser\User Data""")
        this.absPath = bravePath
    }
}