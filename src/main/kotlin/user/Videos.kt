package user

import java.nio.file.Path

class Videos:User(){
    override val absPath: Path
    override val name: String = "Videos"
    init{
        val envPath:String = System.getenv("USERPROFILE")
        val videosPath:Path = Path.of("""$envPath\Videos""")
        this.absPath = videosPath
    }
}