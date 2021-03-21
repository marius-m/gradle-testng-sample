package lt.markmerkk.processor

import lt.markmerkk.TestUtils
import lt.markmerkk.parametrized.TestInputTemplateImage1
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.DataProvider
import org.testng.annotations.Test

class DocumentProcessorParametrizedTest {

    @DataProvider(name = "input1")
    fun pdfs(): Array<TestInputTemplateImage1> {
        return generateTemplates().toTypedArray()
    }

    @Test(dataProvider = "input1")
    fun valid(testInput: TestInputTemplateImage1) {
        // Assemble
        val docProcessor = DocumentProcessor()
        val imageFile = TestUtils.projectResAsFile(testInput.path)

        // Act
        val result = docProcessor.readDocument(imageFile)

        // Assert
        assertThat(result).isEqualTo(testInput.expectTemplateType)
    }

    companion object {
        fun generateTemplates(): List<TestInputTemplateImage1> {
            val inputPdfPath = "/image1.png"
            return listOf(
                TestInputTemplateImage1(
                    path = inputPdfPath,
                    expectTemplateType = "1234.0"
                ),
                TestInputTemplateImage1(
                    path = inputPdfPath,
                    expectTemplateType = "1234.5"
                ),
            )
        }
    }

}