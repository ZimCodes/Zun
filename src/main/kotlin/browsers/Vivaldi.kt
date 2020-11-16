package browsers

import java.nio.file.Path

class Vivaldi:Chromium(){
    override val absPath: Path
    override val name:String = "Vivaldi"
    init{
        val envPath:String = System.getenv("LOCALAPPDATA")
        val vivaldiPath:Path = Path.of("""$envPath\Vivaldi\User Data""")
        this.absPath = vivaldiPath
    }
}