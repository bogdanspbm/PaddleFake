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
}