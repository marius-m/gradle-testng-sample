package lt.markmerkk

import java.io.File

object TestUtils {

    fun projectResAsFile(testSource: String): File {
        return File("test-input-images", testSource)
    }

    fun resAsFile(testSource: String): File {
        return File(this.javaClass.getResource(testSource).file)
    }
}