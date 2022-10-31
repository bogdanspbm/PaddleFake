package utils

import exception.UnexpectedLineException
import objects.Command
import javax.swing.text.StyledEditorKit.BoldAction

class Parser {

    fun parse(lines: List<String>): List<Command> {
        var res = ArrayList<Command>()
        var lastCommand: Command? = null
        var lastModule: String = ""

        lines.forEach {
            var line = it
            if (isNewCommand(line)) {

                // Save previous Command
                if (lastCommand != null) {
                    res.add(lastCommand!!)
                }

                lastCommand = Command(line.replace(":", ""))
            } else if (isCommandModule(line)) {

                if (!extractModule(line).equals("")) {
                    lastModule = extractModule(line)
                }

                when (extractModule(line)) {
                    "dependencies" -> {

                    }

                    "target" -> {
                        var parts = line.split(":")
                        if (parts.size > 1) {
                            var target = parts[1].trim()
                            lastCommand!!.target = target
                        } else {
                            throw UnexpectedLineException("Bad line: " + line)
                        }
                    }

                    "run" -> {
                        var parts = line.split(":")
                        if (parts.size > 1) {
                            var run = parts[1].trim()
                            lastCommand!!.run = run
                        } else {
                            throw UnexpectedLineException("Bad line: " + line)
                        }
                    }

                    "" -> {
                        if (lastModule.equals("dependencies")) {
                            var parts = line.split("-")
                            if (parts.size > 1) {
                                var depend = parts[1].trim()
                                lastCommand!!.dependencies.add(depend)
                            } else {
                                throw UnexpectedLineException("Bad line: " + line)
                            }
                        } else if (!line.trim().equals("") && !line.trim().equals("\n")) {
                            throw UnexpectedLineException("Bad line: " + line)
                        }
                    }
                }
            }
        }

        if (lastCommand != null) {
            res.add(lastCommand!!)
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

        if (line.startsWith("\t")) {
            return false
        }

        if (line.startsWith("\n")) {
            return false
        }

        if (line.equals("")) {
            return false
        }

        if (line.endsWith(":")) {
            return true
        } else {
            throw UnexpectedLineException("Bad line: " + line)
        }
    }
}