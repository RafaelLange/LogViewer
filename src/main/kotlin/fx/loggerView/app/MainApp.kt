package fx.loggerView.app

import fx.loggerView.views.MainView
import tornadofx.App
import tornadofx.launch

class MainApp: App(MainView::class)

fun main(args: Array<String>) {
    launch<MainApp>()
}