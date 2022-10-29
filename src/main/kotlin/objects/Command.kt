package objects

class Command {
    var name: String = ""
    var dependencies: List<String> = ArrayList<String>()
    var target: String = ""
    var run: String = ""

    constructor(name: String) {
        this.name = name
    }
}