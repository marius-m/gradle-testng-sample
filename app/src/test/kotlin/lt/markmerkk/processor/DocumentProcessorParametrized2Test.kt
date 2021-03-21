package lt.markmerkk.processor

import lt.markmerkk.TestUtils
import lt.markmerkk.parametrized.TestInputTemplateImage1
import lt.markmerkk.parametrized.TestInputTemplateImage2
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.SoftAssertions
import org.testng.annotations.DataProvider
import org.testng.annotations.Test

class DocumentProcessorParametrized2Test {

    @DataProvider(name = "input1")
    fun pdfs(): Array<TestInputTemplateImage2> {
        return generateTemplates().toTypedArray()
    }

    @Test(dataProvider = "input1")
    fun valid(testInput: TestInputTemplateImage2) {
        // Assemble
        val sa = SoftAssertions()
        val docProcessor = DocumentProcessor()
        val imageFile = TestUtils.projectResAsFile(testInput.path)

        // Act
        val result = docProcessor.readDocument(imageFile)

        // Assert
        testInput.assertThat(sa, result)
        sa.assertAll()
    }

    companion object {
        fun generateTemplates(): List<TestInputTemplateImage2> {
            val inputPdfPath = "/image1.png"
            return listOf(
                TestInputTemplateImage2(
                    path = inputPdfPath,
                    expectTemplateType = "1234.0"
                ),
                TestInputTemplateImage2(
                    path = inputPdfPath,
                    expectTemplateType = "1234.5"
                ),
            )
        }
    }

}