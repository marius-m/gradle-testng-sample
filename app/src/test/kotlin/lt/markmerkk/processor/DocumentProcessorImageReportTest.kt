package lt.markmerkk.processor

import lt.markmerkk.ReportUtils
import lt.markmerkk.TestUtils
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

class DocumentProcessorImageReportTest {
    @Test
    fun testValid1() {
        // Assemble
        val docProcessor = DocumentProcessor()
        val imageFile = TestUtils.projectResAsFile("/image1.png")

        // Act
        val result = docProcessor.readDocument(imageFile)

        // Assert
        ReportUtils.reportMessage("Custom message1")
        ReportUtils.reportImages(listOf(imageFile, imageFile, imageFile))
        assertThat(result).isEqualTo("111.0")
    }
}