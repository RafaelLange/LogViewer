package fx.loggerView.views

import javafx.scene.Parent
import tornadofx.*

class MainView: View() {
    override val root = borderpane {
        top(Upper::class)
        center(LogList::class)
    }
}