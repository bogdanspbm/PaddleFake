import utils.Controller


open class Application {
        companion object {
                @JvmStatic fun main(args: Array<String>) {
                        var controller = Controller()
                        controller.scan("fake.yaml", args)
                }
        }
}
