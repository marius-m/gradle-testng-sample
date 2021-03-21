package lt.markmerkk.processor

import lt.markmerkk.TestUtils
import lt.markmerkk.parametrized.TestExpectTemplateEmpty
import lt.markmerkk.parametrized.TestExpectTemplateNumbers
import lt.markmerkk.parametrized.TestExpectTemplateText
import lt.markmerkk.parametrized.TestInputTemplateImage1
import lt.markmerkk.parametrized.TestInputTemplateImage2
import lt.markmerkk.parametrized.TestInputTemplateImage3
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.SoftAssertions
import org.testng.annotations.DataProvider
import org.testng.annotations.Test

class DocumentProcessorParametrized3Test {

    @DataProvider(name = "input1")
    fun pdfs(): Array<TestInputTemplateImage3> {
        return generateTemplates().toTypedArray()
    }

    @Test(dataProvider = "input1")
    fun valid(testInput: TestInputTemplateImage3) {
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
        fun generateTemplates(): List<TestInputTemplateImage3> {
            val inputPdfPath = "/image1.png"
            return listOf(
                TestInputTemplateImage3(
                    path = inputPdfPath,
                    expectObject = TestExpectTemplateEmpty()
                ),
                TestInputTemplateImage3(
                    path = inputPdfPath,
                    expectObject = TestExpectTemplateNumbers(1234.5)
                ),
                TestInputTemplateImage3(
                    path = inputPdfPath,
                    expectObject = TestExpectTemplateText("1234.5")
                ),
            )
        }
    }

}