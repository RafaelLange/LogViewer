package fx.loggerView.views

import fx.loggerView.controllers.LoggerController
import javafx.geometry.Pos
import javafx.stage.FileChooser
import tornadofx.*

class Upper : View("My View") {
    val loggerController: LoggerController by inject()

    override val root = borderpane {
        left {
            paddingAll = 5
            button("Import Log") {
                action {
                    val filter = arrayOf(FileChooser.ExtensionFilter("Log file (*.html)", "*.html"))
                    val fileList = chooseFile("Select a Log", filter)
                    runAsyncWithProgress {
                        loggerController.importFileLog(fileList)
                    }
                }
            }
        }
        right {
            paddingAll = 5
            hbox {
                hbox {
                    textfield {
                        promptText = "Search"
                        action {
                            runAsyncWithProgress {
                                loggerController.searchRequest(text)
                            } ui {
                                loggerController.currentMatchedRow()
                            }
                        }
                    }
                    button {
                        graphic = svgpath {
                            content = "M3.204 5L8 10.481 12.796 5H3.204zm-.753.659l4.796 5.48a1 1 0 0 0 1.506 0l4.796-5.48c.566-.647.106-1.659-.753-1.659H3.204a1 1 0 0 0-.753 1.659z"
                        }
                        action {
                            loggerController.nextMatchedRow()
                        }
                    }
                    button {
                        graphic = svgpath {
                            content = "M3.204 11L8 5.519 12.796 11H3.204zm-.753-.659l4.796-5.48a1 1 0 0 1 1.506 0l4.796 5.48c.566.647.106 1.659-.753 1.659H3.204a1 1 0 0 1-.753-1.659z"
                        }
                        action {
                            loggerController.previousMatchedRow()
                        }
                    }
                }
                spacing = 10.0
                paddingHorizontal = 10
                hbox {
                    alignment = Pos.CENTER
                    label("match: ")
                    label(loggerController.currentMatchedIndexProperty)
                    label(" of ")
                    label(loggerController.totalMatchedRowsProperty)
                }
            }
        }
    }
}
