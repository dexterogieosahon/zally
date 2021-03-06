package de.zalando.zally.rule

import com.typesafe.config.Config
import de.zalando.zally.violation.Violation
import de.zalando.zally.violation.ViolationType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class AvoidLinkHeadersRule(@Autowired rulesConfig: Config) : HttpHeadersRule(rulesConfig) {
    override val title = "Avoid Link in Header Rule"
    override val violationType = ViolationType.MUST
    override val url = "http://zalando.github.io/restful-api-guidelines/hyper-media/Hypermedia.html" +
        "#must-do-not-use-link-headers-with-json-entities"
    override val code = "M001"
    private val DESCRIPTION = "Do Not Use Link Headers with JSON entities"

    override fun isViolation(header: String) = header == "Link"

    override fun createViolation(paths: List<String>): Violation {
        return Violation(this, title, DESCRIPTION, violationType, url, paths)
    }
}
