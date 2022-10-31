package utils

import exception.DependencyException
import exception.DependencyLoopException
import exception.ExecuteException
import exception.UniqueCommandException
import objects.Command
import java.io.File
import java.util.*

class Controller {

    var parser = Parser()
    var fileReader = FileReader()
    var commandMap = HashMap<String, Command>()


    fun scan(path: String, args: Array<String>?) {
        commandMap = HashMap<String, Command>()

        var fileContent = fileReader.getFileContent(path)
        var commands = parser.parse(fileContent)


        // Building command Map
        commands.forEach {
            if (!commandMap.contains(it.name)) {
                commandMap.put(it.name, it)
            } else {
                throw UniqueCommandException("Command " + it.name + " duplicates")
            }
        }

        // Clear Before
        if (commandMap.containsKey("clean") || (args != null && args.contains("clean"))) {
            executeClean()
        }

        // Execute commands
        if (args == null || args.size == 0) {
            commands.forEach {
                if (!commandMap.get(it.name)!!.getExecuted()) {
                    executeCommand(it, ArrayList())
                }
            }
        } else {
            args.forEach {
                if (commandMap.get(it) != null && !commandMap.get(it)!!.getExecuted()) {
                    executeCommand(commandMap.get(it)!!, ArrayList())
                }
            }
        }
    }

    private fun executeClean() {
        commandMap.forEach {
            var file = it.value.target
            if (File(file).exists()) {
                File(file).delete()
            }
        }

        if (commandMap.containsKey("clean")) {
            commandMap.get("clean")!!.setExecuted()
        }
    }

    private fun executeCommand(cmd: Command, history: List<String>) {
        if (history.contains(cmd.name)) {
            throw DependencyLoopException("Diagnosed command loop dependency: \n" + history.joinToString("\n"))
        }


        cmd.dependencies.forEach {
            var depend = it

            if (commandMap.contains(depend)) {
                var dependCmd = commandMap.get(depend)
                if (!commandMap.get(dependCmd!!.name)!!.getExecuted()) {
                    var nextHistory = history.toMutableList()
                    nextHistory.add(cmd.name)
                    executeCommand(dependCmd, nextHistory)
                }

                if (!commandMap.get(dependCmd.name)!!.getExecuted()) {
                    throw DependencyException("Can't complete dependency: " + dependCmd.name)
                }
            } else if (!File(depend).exists()) {
                throw DependencyException("Can't complete dependency: " + depend)
            }
        }

        try {
            if (!File(cmd.target).exists()) {
                var proc = Runtime.getRuntime().exec(cmd.run)
                System.out.println(cmd.run)
            }
            commandMap.get(cmd.name)!!.setExecuted()
        } catch (e: Exception) {
            throw ExecuteException("Can't execute command: " + cmd.run)
        }
    }
}