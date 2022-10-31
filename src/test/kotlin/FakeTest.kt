import exception.*
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

    @Test
    fun testLoopDependecyTask() {
        var controller = Controller()
        try {
            controller.scan("files/testC.yaml", null)
        } catch (e: DependencyLoopException) {
            return
        }

        assert(false)
    }
    @Test
    fun testDependencyTask() {
        var controller = Controller()
        try {
            controller.scan("files/testD.yaml", null)
        } catch (e: DependencyException) {
            return
        }

        assert(false)
    }

    @Test
    fun testBadLine() {
        var controller = Controller()
        try {
            controller.scan("files/testE.yaml", null)
        } catch (e: UnexpectedLineException) {
            return
        }

        assert(false)
    }
}