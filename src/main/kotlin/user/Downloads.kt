package user
import java.nio.file.Path

class Downloads:User() {
    override val absPath: Path
    override val name: String = "Downloads"
    init{
        val envPath:String = System.getenv("USERPROFILE")
        val downloadsPath:Path = Path.of("""$envPath\Downloads""")
        this.absPath = downloadsPath
    }
}