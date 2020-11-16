package manager
import Cleaner
import browsers.*

class BrowserCleaner:CleanManager() {
    override val name: String = "Browser"
    override val items:Array<Cleaner> = arrayOf(
            Firefox(),
            Chrome(),
            Edge(),
            Brave(),
            Vivaldi()
    )
}