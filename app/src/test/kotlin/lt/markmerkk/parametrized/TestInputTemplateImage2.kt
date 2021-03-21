package lt.markmerkk.parametrized

import org.assertj.core.api.SoftAssertions

data class TestInputTemplateImage2(
    val path: String,
    val expectTemplateType: String
) {
    fun assertThat(sa: SoftAssertions, result: String) {
        sa.assertThat(result).isEqualTo(expectTemplateType)
    }
}
