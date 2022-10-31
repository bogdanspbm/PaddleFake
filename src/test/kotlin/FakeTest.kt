import exception.ExecuteException
import exception.UniqueCommandException
import org.junit.Test

import utils.Controller

class FakeTest {

    @Test
    fun testCloneTask() {
        var controller = Controller()
        try {
            controller.start("files/testA.yaml")
        } catch (e: UniqueCommandException) {
            return
        }

        assert(false)
    }

    @Test
    fun testExecuteTask() {
        var controller = Controller()
        try {
            controller.start("files/testB.yaml")
        } catch (e: ExecuteException) {
            return
        }

        assert(false)
    }
}