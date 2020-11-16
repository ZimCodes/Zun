package manager
import kotlinx.coroutines.Job

abstract class Manager<T> {
    protected abstract val items:Array<T>
    protected abstract val name:String

    protected abstract suspend fun startCleaning(): Job

    suspend fun start(){
        println("!!!--$name Cleaning has started--!!!")

        val cleanJob = this.startCleaning()
        cleanJob.join()

        if(cleanJob.isCompleted){
            println("!!!--$name Cleaning finished--!!!")
        }

    }

}