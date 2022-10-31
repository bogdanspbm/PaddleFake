import utils.Controller


open class Application {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            if (args.size > 0) {
                var controller = Controller()
                controller.scan(args[0], args)
            }
        }
    }
}
