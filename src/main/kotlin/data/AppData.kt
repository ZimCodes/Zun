package data

import TempCleaner
import java.nio.file.Path

open class AppData: TempCleaner(){
    override val absPath: Path
    override val name:String = "AppData"
    init{
        val envPath:String = System.getenv("USERPROFILE")
        val appPath:Path = Path.of("""$envPath\AppData""")
        this.absPath = appPath
    }

    override suspend fun clean() {

    }
}