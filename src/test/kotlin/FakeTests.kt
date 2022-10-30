import exception.UniqueCommandException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import utils.Controller

class FakeTests {

    @Test
    fun testCloneTask() {
        var controller = Controller()
        try {
            controller.start("files/testA.yaml")
        } catch (e: UniqueCommandException) {
            return
        }
        Assertions.assertTrue(false)
    }
}