package fx.loggerView.views

import fx.loggerView.controllers.LoggerController
import fx.loggerView.models.Log
import javafx.event.EventHandler
import tornadofx.*

class LogList : View() {
    private val loggerController: LoggerController by inject()

    override val root = tableview(loggerController.logRows) {
        column("Id", Log::id).contentWidth()
        column("StageName", Log::stageName).enableTextWrap()
        column("StageType", Log::stageType).prefWidth(70)
        column("Page", Log::page).enableTextWrap()
        column("Object", Log::obj).enableTextWrap()
        column("Action", Log::action).enableTextWrap().prefWidth(100)
        column("Result", Log::result)
        column("Resource Start", Log::resourceStart).enableTextWrap().fixedWidth(70)
        column("Resource End", Log::resourceEnd).enableTextWrap().fixedWidth(70)
        column("Direc.", Log::direction).fixedWidth(50)
        column("Name", Log::name)
        column("Field", Log::field)
        column("Row", Log::row).fixedWidth(30)
        column("Type", Log::type)
        column("Value", Log::value).remainingWidth().enableTextWrap()

        columns.forEach {
            it.isSortable = false
        }

        columnResizePolicy = SmartResize.POLICY

        loggerController.currentMatchedRowsProperty.onChange {
            selectionModel.select(loggerController.currentMatchedRows)
            focusModel.focus(loggerController.currentMatchedRows)
            scrollTo(loggerController.currentMatchedRows)
            requestFocus()
        }
    }
}