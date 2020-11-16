import java.nio.file.Path

interface ICleaner {
    suspend fun clean():Unit
}