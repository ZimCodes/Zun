package user
import java.nio.file.Path

class Pictures:User() {
    override val absPath: Path
    override val name: String = "Pictures"
    init{
        val envPath:String = System.getenv("USERPROFILE")
        val picsPath:Path = Path.of("""$envPath\Pictures""")
        this.absPath = picsPath
    }
}