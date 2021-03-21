package lt.markmerkk.processor

import lt.markmerkk.TestUtils
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.SoftAssertions
import org.testng.annotations.Test
import org.testng.asserts.SoftAssert

class DocumentProcessorSoftAssertTest {

    @Test
    fun valid() {
        // Assemble
        val sa = SoftAssertions()
        val docProcessor = DocumentProcessor()
        val imageFile = TestUtils.projectResAsFile("/image1.png")

        // Act
        val result = docProcessor.readDocument(imageFile)

        // Assert
        sa.assertThat(imageFile.exists()).isTrue()
        sa.assertThat(result).isEqualTo("111.0")
        sa.assertThat(result).isEqualTo("444.0")
        sa.assertAll()
    }

}