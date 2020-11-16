import java.io.File
import java.nio.file.Files
import java.nio.file.Path

abstract class Cleaner:ICleaner {
    abstract val absPath:Path
    abstract override suspend fun clean()
    protected fun pathExists() =
            absPath.toFile().exists()
    /// Retrieve certain Files in the current Directory
    private fun dirStream(path: String, glob: String):List<Path>{
        val newPath:Path = Path.of(path)
        if(newPath.toFile().exists()){
            return Files.newDirectoryStream(newPath, glob).toList()
        }
        return emptyList()
    }
    /// Retrieve all Files in the current Directory
    protected fun dirStream(path:String):List<Path>{
        val newPath:Path = Path.of(path)
        if(newPath.toFile().exists()){
            return Files.newDirectoryStream(newPath).toList()
        }
        return emptyList()
    }
    /// Retrieve certain Files in the current Directory & delete them
    protected fun deleteDirStream(path: String, glob: String) =
            this.delete(this.dirStream(path,glob))

    /// Retrieve all Files in the current Directory & delete them
    protected fun deleteDirStream(path: String) =
        this.delete(this.dirStream(path))

    /// Delete all files in the current directory. *Including Current Directory
    private fun delete(paths: Collection<Path>) {
        if(!paths.isEmpty()){
            for(path in paths){
                val file = path.toFile()
                if(file.exists()){
                    file.deleteRecursively()
                }
            }
        }
    }
    /// Delete a file/directory
    protected fun delete(path: Path) {
        val file = path.toFile()
        if(file.exists()){
            file.deleteRecursively()
        }
    }
    /// Delete a file/directory
    protected fun delete(path:String){
        val newPath:Path = Path.of(path)
        val file = newPath.toFile()
        if(file.exists()){
            file.deleteRecursively()
        }
    }


    ///Delete certain folders while recursively navigating through directories
    protected fun recursiveFileDeleter(path:Path, fileExcludes:Array<Regex>, dirExcludes:Array<Regex> = emptyArray()){
        // Current directories to look through
        var dirOfDirs:List<List<Path>> = listOf(Files.newDirectoryStream(path).toList())
        println("\n!!---Starting Recursive Cleansing---!!\n")
        while(dirOfDirs.isNotEmpty()){
            //Supply new directories to look through
            val newDirs:MutableList<List<Path>> = mutableListOf()
            for(dirs in dirOfDirs){
                for(dir in dirs){
                    val dirSet:List<Path> = this.recursiveDirs(dir,fileExcludes,dirExcludes)
                    //Checks if directories in current folder exists
                    if (dirSet.isNotEmpty()){
                        newDirs.add(dirSet)
                    }
                }
            }
            //Resupply new directories to look through if some exists
                dirOfDirs = newDirs

        }
        println("!!---End of Recursive Cleansing---!!")
    }
    // Recursively move through Directories
    private fun recursiveDirs(path: Path, fileExcludes:Array<Regex>,dirExcludes:Array<Regex>):List<Path>{
        val newDirs = mutableListOf<Path>()
        val dirs:List<Path>
        try {
          dirs = Files.newDirectoryStream(path).toList()
        }catch (e:Exception){
            return emptyList()
        }
        if(dirs.isNotEmpty()){
            //Change Paths to a File object
            // File object can either be a directory or a file
            val files:List<File> = dirs.map { it.toFile() }
            this.recurseDelete(files,fileExcludes,dirExcludes,newDirs)
        }
        return newDirs
    }
    //Deletes Directories from exclusion list & returns more Directories that were found
    private fun recurseDelete(files:List<File>, fileExcludes:Array<Regex>,
                              dirExcludes:Array<Regex>, newDirs:MutableList<Path>){
        for(f in files){
            if(f.exists()){
                if (f.isDirectory){
                    println("Directory: $f")
                    println("**Cleaning Directory! Please wait...**\n")
                    for(ex in dirExcludes){
                        if(ex.containsMatchIn(f.toString())) {
                            f.deleteRecursively()
                            break
                        }
                    }
                    newDirs.add(f.toPath())
                }else if (f.isFile){
                    for(ex in fileExcludes){
                        if(ex.containsMatchIn(f.toString())){
                            f.delete()
                        }
                    }
                }
            }
        }
    }
}