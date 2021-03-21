package lt.markmerkk.processor

import lt.markmerkk.TestConsts
import lt.markmerkk.TestUtils
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

@Test(groups = [TestConsts.GROUP_PDF_DETAILS])
class DocumentProcessorGroupTest {
    @Test
    fun valid() {
        // Assemble
        val docProcessor = DocumentProcessor()
        val imageFile = TestUtils.projectResAsFile("/image1.png")

        // Act
        val result = docProcessor.readDocument(imageFile)

        // Assert
        assertThat(imageFile.exists()).isTrue()
        assertThat(result).isEqualTo("111.0")
    }
}