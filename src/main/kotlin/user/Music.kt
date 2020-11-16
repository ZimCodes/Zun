package user
import java.nio.file.Path

class Music:User() {
    override val absPath: Path
    override val name: String = "Music"
    init{
        val envPath:String = System.getenv("USERPROFILE")
        val musicPath:Path = Path.of("""$envPath\Music""")
        this.absPath = musicPath
    }
}