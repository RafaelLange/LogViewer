package fx.loggerView.views

import javafx.scene.Parent
import tornadofx.*

class HelpView: View("About") {
    override val root = vbox{
        paddingAll = 20
        spacing = 10.0
        hbox {
            spacing = 10.0
            label("Created by:")
            label("Rafael Guilherme Lange")
        }

        hbox {
            spacing = 10.0
            label("Version:")
            label("1.0")
        }

        hbox {
            spacing = 10.0
            label("Source:")
            hyperlink("https://github.com/RafaelLange/LogViewer") {
                action {
                    ProcessBuilder("x-www-browser", text)
                }
            }
        }

        hbox {
            spacing = 10.0
            label("E-mail:")
            hyperlink("rafaelguilhermelange@gmail.com") {
                action {
                    ProcessBuilder("x-www-browser", text)
                }
            }
        }
    }
}