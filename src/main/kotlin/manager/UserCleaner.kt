package manager
import Cleaner
import user.*
class UserCleaner:CleanManager() {
    override val name: String = "User"
    override val items: Array<Cleaner> = arrayOf(
            Pictures(),
            Videos(),
            Downloads(),
            Music(),
    )
}