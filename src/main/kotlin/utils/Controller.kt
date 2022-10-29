package utils

import java.io.File

class Controller {

    var parser = Parser()
    var fileReader = FileReader()


    fun start() {
        var fileContent = fileReader.getFileContent("fake.yaml")
        var tree = parser.parse(fileContent)
    }
}