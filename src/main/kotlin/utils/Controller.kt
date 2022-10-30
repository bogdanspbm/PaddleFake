package utils

import exception.DependencyException
import exception.DependencyLoopException
import exception.UniqueCommandException
import objects.Command
import java.io.File

class Controller {

    var parser = Parser()
    var fileReader = FileReader()
    var commandMap = HashMap<String, Command>()


    fun start() {
        commandMap = HashMap<String, Command>()

        var fileContent = fileReader.getFileContent("fake.yaml")
        var commands = parser.parse(fileContent)


        // Building command Map
        commands.forEach {
            if (!commandMap.contains(it.name)) {
                commandMap.put(it.name, it)
            } else {
                throw UniqueCommandException("Command " + it.name + " duplicates")
            }
        }

        // Execute commands
        commands.forEach {
            if (!commandMap.get(it.name)!!.getExecuted()) {
                executeCommand(it, ArrayList())
            }
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

        if (!File(cmd.target).exists()) {
            var proc = Runtime.getRuntime().exec(cmd.run)
            System.out.println(cmd.run)
            System.out.println(proc.outputStream.toString())
        }


        commandMap.get(cmd.name)!!.setExecuted()
    }
}