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
                hbox {
                    paddingLeft = 10
                    paddingRight = 50
                    alignment = Pos.CENTER
                    label("match: ")
                    label(loggerController.currentMatchedIndexProperty)
                    label(" of ")
                    label(loggerController.totalMatchedRowsProperty)
                }
                hbox {
                    alignment = Pos.CENTER
                    button {
                        svgpath {
                            content = "M5.255 5.786a.237.237 0 0 0 .241.247h.825c.138 0 .248-.113.266-.25.09-.656.54-1.134 1.342-1.134.686 0 1.314.343 1.314 1.168 0 .635-.374.927-.965 1.371-.673.489-1.206 1.06-1.168 1.987l.003.217a.25.25 0 0 0 .25.246h.811a.25.25 0 0 0 .25-.25v-.105c0-.718.273-.927 1.01-1.486.609-.463 1.244-.977 1.244-2.056 0-1.511-1.276-2.241-2.673-2.241-1.267 0-2.655.59-2.75 2.286zm1.557 5.763c0 .533.425.927 1.01.927.609 0 1.028-.394 1.028-.927 0-.552-.42-.94-1.029-.94-.584 0-1.009.388-1.009.94z"
                        }

                        action {
                            find(HelpView::class).openModal()
                        }
                    }
                }
            }
        }
    }
}
