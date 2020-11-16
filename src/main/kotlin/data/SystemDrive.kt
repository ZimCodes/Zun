package data
import Cleaner
import java.nio.file.Path

class SystemDrive:Cleaner() {
    override val absPath: Path
    init{
        val envPath:String = System.getenv("SYSTEMDRIVE")
        val systemPath:Path = Path.of(envPath)
        this.absPath = systemPath
    }

    override suspend fun clean() {
        if(this.pathExists()){
            println("Xxx--Begin Cleaning System Drive--xxX")
            this.msoCache()
            this.temp()
            this.tmp()
            println("Xxx--System Drive Cleaning Ends--xxX")
        }
    }
    private fun msoCache(){
        val path = """${this.absPath}\MSOCache"""
        this.delete(path)
    }
    private fun temp(){
        val path = """${this.absPath}\Temp"""
        this.deleteDirStream(path)
    }
    private fun tmp(){
        val path = """${this.absPath}\tmp"""
        this.deleteDirStream(path)
    }
}