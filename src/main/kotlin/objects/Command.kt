package objects

class Command {
    var name: String = ""
    var dependencies: ArrayList<String> = ArrayList<String>()
    var target: String = ""
    var run: String = ""

    constructor(name: String) {
        this.name = name
    }

    override fun toString(): String {
        var res = name + ":\n"
        if (dependencies.size > 0) {
            res += "\tdependencies:\n"
            dependencies.forEach { res += "\t\t- " + it + "\n" }
        }
        res += "\ttarget: " + target + "\n"
        res += "\trun: " + run + "\n"
        return res
    }


}