package utils

import java.io.File
import java.nio.file.Files

class FileReader {
    fun getFileContent(path: String): List<String> {
        var file = File(path)

        if (!file.exists()) {
            return arrayListOf()
        }

        return Files.readAllLines(file.toPath(), Charsets.UTF_8)
    }
}