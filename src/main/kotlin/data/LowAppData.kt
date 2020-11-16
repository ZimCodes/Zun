package data

import java.nio.file.Path

class LowAppData:AppData() {
    override val absPath: Path
    override val name: String = "Local Low AppData"
    init{
        val envPath:String = System.getenv("USERPROFILE")
        val lowPath: Path = Path.of("""$envPath\AppData\LocalLow""")
        this.absPath = lowPath
    }
}