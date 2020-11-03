package fx.loggerView.models

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.*

class Log {
    val idProperty = SimpleIntegerProperty(0)
    var id: Int by idProperty

    val stageNameProperty = SimpleStringProperty("")
    var stageName: String by stageNameProperty

    val stageTypeProperty = SimpleStringProperty("")
    var stageType: String by stageTypeProperty

    val pageProperty = SimpleStringProperty("")
    var page: String by pageProperty

    val objProperty = SimpleStringProperty("")
    var obj: String by objProperty

    val actionProperty = SimpleStringProperty("")
    var action: String by actionProperty

    val resultProperty = SimpleStringProperty("")
    var result: String by resultProperty

    val resourceStartProperty = SimpleStringProperty("")
    var resourceStart: String by resourceStartProperty

    val resourceEndProperty = SimpleStringProperty("")
    var resourceEnd: String by resourceEndProperty

    val directionProperty = SimpleStringProperty("")
    var direction: String by directionProperty

    val nameProperty = SimpleStringProperty("")
    var name: String by nameProperty

    val fieldProperty = SimpleStringProperty("")
    var field: String by fieldProperty

    val rowProperty = SimpleStringProperty("")
    var row: String by rowProperty

    val typeProperty = SimpleStringProperty("")
    var type: String by typeProperty

    val valueProperty = SimpleStringProperty("")
    var value: String by valueProperty

    fun contain(text: String): Boolean {
        val contents = listOf(id.toString(), stageName, stageType, page, obj, action, result, resourceStart, resourceEnd, direction, name, field, row, type, value)
        val contentsText = contents.joinToString()
        return contentsText.contains(text, ignoreCase = true)
    }
}