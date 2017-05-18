import com.github.adeynack.kotti.KotlinCoreImplementation
import com.github.adeynack.kotti.tests.KotlinTestsImplementation
import org.amshove.kluent.shouldEqual
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

object TestsSpec : Spek({

    describe("kotti-tests") {

        it("should be true") {
            KotlinTestsImplementation.value shouldEqual true
        }

        it("should be able to use things from kotti-core") {
            KotlinCoreImplementation.value shouldEqual true
        }

    }

})
