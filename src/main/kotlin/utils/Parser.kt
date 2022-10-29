package utils

import exception.UnexpectedLineException
import objects.Command
import javax.swing.text.StyledEditorKit.BoldAction

class Parser {

    fun parse(lines: List<String>): List<Command> {
        var res = ArrayList<Command>()
        var lastCommand: Command? = null


        lines.forEach {
            var line = it
            if (isNewCommand(line)) {

                // Save previous Command
                if (lastCommand != null) {
                    res.add(lastCommand!!)
                }

                lastCommand = Command(line)
            } else if (isCommandModule(line)) {
                when (extractModule(line)) {
                    "dependencies" -> {

                    }

                    "target" -> {

                    }

                    "run" -> {

                    }
                }
            }
        }

        return res
    }

    private fun isCommandModule(line: String): Boolean {
        if (line.startsWith(" ")) {
            return true
        }

        if (line.startsWith("\t")) {
            return true
        }

        return false
    }

    private fun extractModule(line: String): String {
        if (line.contains("dependencies:")) {
            return "dependencies"
        }

        if (line.contains("target:")) {
            return "target"
        }

        if (line.contains("run:")) {
            return "run"
        }

        return ""
    }

    private fun isNewCommand(line: String): Boolean {
        if (line.startsWith(" ")) {
            return false
        }

        if (line.startsWith("/t")) {
            return false
        }

        if (line.endsWith(":")) {
            return true
        } else {
            throw UnexpectedLineException("Bad line: " + line)
        }
    }
}