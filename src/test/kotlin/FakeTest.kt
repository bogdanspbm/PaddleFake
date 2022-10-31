import exception.ExecuteException
import exception.UniqueCommandException
import org.junit.Test

import utils.Controller

class FakeTest {

    @Test
    fun testCloneTask() {
        var controller = Controller()
        try {
            controller.scan("files/testA.yaml", null)
        } catch (e: UniqueCommandException) {
            return
        }

        assert(false)
    }

    @Test
    fun testExecuteTask() {
        var controller = Controller()
        try {
            controller.scan("files/testB.yaml", null)
        } catch (e: ExecuteException) {
            return
        }

        assert(false)
    }
}