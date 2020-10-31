package fx.loggerView.controllers

import fx.loggerView.models.Log
import javafx.beans.property.SimpleIntegerProperty
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import tornadofx.*
import java.io.File

class CircularMutableList<T> {
    private val items = FXCollections.observableArrayList<T>()
    var currentIndex = 0

    fun next(): T {
        currentIndex++
        if (currentIndex == items.size) {
            currentIndex = 0
        }
        return items[currentIndex]
    }

    fun previous(): T {
        currentIndex--
        if (currentIndex < 0) {
            currentIndex = items.size - 1
        }
        return items[currentIndex]
    }

    fun add(i: T) {
        items.add(i)
    }

    fun clear() {
        items.clear()
        currentIndex = 0
    }

    fun first(): T {
        return items.first()
    }

    val size: Int
        get() = items.size

    fun isEmpty(): Boolean {
        return items.isEmpty()
    }
}

class LoggerController : Controller() {
    private val rowPatternRegex = Regex("(?<=<TR>).*?(?=</TR>)", RegexOption.IGNORE_CASE)
    private val cellPatternRegex = Regex("(?<=<TD>).*?(?=</TD>)", RegexOption.IGNORE_CASE)
    var logRows: ObservableList<Log> = FXCollections.observableArrayList()
    var matchedRowsSearch = CircularMutableList<Int>()

    val currentMatchedRowsProperty = SimpleIntegerProperty(0)
    var currentMatchedRows by currentMatchedRowsProperty

    val totalMatchedRowsProperty = SimpleIntegerProperty(0)
    var totalMatchedRows by totalMatchedRowsProperty

    val currentMatchedIndexProperty = SimpleIntegerProperty(0)
    var currentMatchedIndex by currentMatchedIndexProperty

    fun importFileLog(fileList: List<File>) {
        if (fileList.isEmpty()) {
            return
        }
        logRows.clear()
        val file = fileList.first()
        val htmlString = file.readText()
            .replace("\n", "")
            .replace("\r", "")
            .replace("&nbsp;", "")
        var rowIndex = 1
        val rows = rowPatternRegex.findAll(htmlString)
            .filterNot {
                it.value.contains("TH", true)
            }.map {
                val log = Log()
                val cells = cellPatternRegex.findAll(it.value).toList()
                if (cells.size < 14) {
                    return@map null
                }
                log.id = rowIndex
                log.stageName = cells[0].value
                log.stageType = cells[1].value
                log.page = cells[2].value
                log.obj = cells[3].value
                log.action = cells[4].value
                log.result = cells[5].value
                log.resourceStart = cells[6].value
                log.resourceEnd = cells[7].value
                log.direction = cells[8].value
                log.name = cells[9].value
                log.field = cells[10].value
                log.row = cells[11].value
                log.type = cells[12].value
                log.value = cells[13].value
                rowIndex++
                return@map log
            }.toList().filterNotNull()
        logRows.addAll(rows)
    }

    fun searchRequest(text: String) {
        matchedRowsSearch.clear()
        logRows.forEachIndexed { index, log -> if (log.contain(text)) matchedRowsSearch.add(index) }

    }

    fun currentMatchedRow() {
        if (matchedRowsSearch.isEmpty()) {
            return
        }
        currentMatchedRows = matchedRowsSearch.first()
        currentMatchedIndex = matchedRowsSearch.currentIndex + 1
        totalMatchedRows = matchedRowsSearch.size
    }

    fun nextMatchedRow() {
        if (matchedRowsSearch.isEmpty()) return
        currentMatchedRows = matchedRowsSearch.next()
        currentMatchedIndex = matchedRowsSearch.currentIndex + 1
    }

    fun previousMatchedRow() {
        if (matchedRowsSearch.isEmpty()) return
        currentMatchedRows = matchedRowsSearch.previous()
        currentMatchedIndex = matchedRowsSearch.currentIndex + 1
    }

}