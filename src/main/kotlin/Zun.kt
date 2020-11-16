import manager.BrowserCleaner
import manager.DataCleaner
import manager.TempManager
import manager.UserCleaner
import user.Downloads
import user.Music
import user.Pictures
import user.Videos
/// Clean options
enum class CleanType(val opt:Int){
    BROWSER(1),
    QUICK_SYSTEM(2),
    FULL_SYSTEM(3),
    PICTURES(4),
    DOWNLOADS(5),
    MUSIC(6),
    VIDEOS(7),
    USER_CLEANUP(8),
    EXIT(9)
}
class Zun {
    private var input:Int = 0
    private val version:String = "1.0.0"//Version
    suspend fun start(){
        while(this.input != CleanType.EXIT.opt){
            this.askLoop()
            this.executeTask()
            this.endMessage()
        }

    }
    /// User interface to display the choices
    private fun displayChoices(){
        println("====================================================")
        println("   .-') _                   .-') _  \n" +
                "  (  OO) )                 ( OO ) ) \n" +
                ",(_)----.  ,--. ,--.   ,--./ ,--,'  \n" +
                "|       |  |  | |  |   |   \\ |  |\\  \n" +
                "'--.   /   |  | | .-') |    \\|  | ) \n" +
                "(_/   /    |  |_|( OO )|  .     |/  \n" +
                " /   /___  |  | | `-' /|  |\\    |   \n" +
                "|        |('  '-'(_.-' |  | \\   |   \n" +
                "`--------'  `-----'    `--'  `--'   ")
        println("v$version")
        println("====================================================")
        println("How would you like your system clean, this evening?\n")
        println("[1] Browser Cleaning (Brave,Chrome,Chromium Edge,Vivaldi,Firefox)")
        println("[2] Light System Cleaning")
        println("[3] Full System Cleaning (opts: [1]&[2] +excess files/folders)(*WARNING: might take a while!)")
        println("[4] Remove ~Pictures~ Folder contents")
        println("[5] Remove ~Downloads~ Folder contents")
        println("[6] Remove ~Music~ Folder contents")
        println("[7] Remove ~Videos~ Folder contents")
        println("[8] Clean All User contents (opts: [4] through [7])")
        println("[9] Exit")
        println("====================================================")
    }
    /// Asks the user for the type of cleaning they want
    private fun askLoop(){
        while(true)
        {
            this.displayChoices()
            try{
                this.input = readLine()?.toInt() ?: CleanType.EXIT.opt
            }catch(e:NumberFormatException){
                this.input = 0
            }

            when (this.input){
                in 1..CleanType.values().size -> break
                else -> continue
            }
        }
    }
    /// Performs the cleaning operation(s)
    private suspend fun executeTask(){
        when (this.input){
            CleanType.BROWSER.opt -> BrowserCleaner().start()
            CleanType.QUICK_SYSTEM.opt -> DataCleaner().start()
            CleanType.FULL_SYSTEM.opt -> {
                BrowserCleaner().start()
                DataCleaner().start()
                TempManager().start()
            }
            CleanType.PICTURES.opt -> Pictures().clean()
            CleanType.DOWNLOADS.opt -> Downloads().clean()
            CleanType.MUSIC.opt -> Music().clean()
            CleanType.VIDEOS.opt -> Videos().clean()
            CleanType.USER_CLEANUP.opt -> UserCleaner().start()
            else -> {
                println("Very good! I shall shutdown in post haste!")
            }
        }

    }
    /// Finish Message
    private fun endMessage(){
        if(this.input in 1 until CleanType.values().size){
            println("\n                       .-') _  \n" +
                    "                      ( OO ) ) \n" +
                    "   ,------.,-.-') ,--./ ,--,'  \n" +
                    "('-| _.---'|  |OO)|   \\ |  |\\  \n" +
                    "(OO|(_\\    |  |  \\|    \\|  | ) \n" +
                    "/  |  '--. |  |(_/|  .     |/  \n" +
                    "\\_)|  .--',|  |_.'|  |\\    |   \n" +
                    "  \\|  |_)(_|  |   |  | \\   |   \n" +
                    "   `--'    `--'   `--'  `--'   \n" +
                    "\n")
            println("~~~I have finished cleaning. Nice & tidy, just how you like it!\n\n")
        }

    }
}