package lt.markmerkk.parametrized

import org.assertj.core.api.SoftAssertions
import org.assertj.core.data.Offset

data class TestInputTemplateImage3(
    val path: String,
    val expectObject: TestExpectTemplateDetail
) {
    fun assertThat(sa: SoftAssertions, result: String) {
        expectObject.assertThat(sa, result)
    }
}

sealed class TestExpectTemplateDetail {
    abstract fun assertThat(
        sa: SoftAssertions,
        data: String,
    )
}

class TestExpectTemplateEmpty() : TestExpectTemplateDetail() {
    override fun assertThat(
        sa: SoftAssertions,
        data: String,
    ) {
        sa.assertThat(data.isEmpty()).isTrue
    }
}

class TestExpectTemplateNumbers(
    val templateNumbers: Double,
) : TestExpectTemplateDetail() {
    override fun assertThat(
        sa: SoftAssertions,
        data: String,
    ) {
        val dataAsNum = data.toDoubleOrNull()
        sa.assertThat(dataAsNum).isEqualTo(templateNumbers, Offset.offset(0.1))
    }
}

class TestExpectTemplateText(
    val templateText: String,
) : TestExpectTemplateDetail() {

    override fun assertThat(
        sa: SoftAssertions,
        data: String,
    ) {
        sa.assertThat(data).isEqualTo(templateText)
    }
}
