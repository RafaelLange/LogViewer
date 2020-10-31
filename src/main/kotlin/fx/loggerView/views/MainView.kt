package fx.loggerView.views

import javafx.scene.Parent
import tornadofx.*

class MainView: View("LogViewer") {
    override val root = borderpane {
        top(Upper::class)
        center(LogList::class)
    }
}