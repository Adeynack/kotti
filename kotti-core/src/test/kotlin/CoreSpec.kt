import com.github.adeynack.kotti.KotlinCoreImplementation
import org.amshove.kluent.shouldEqual
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

object CoreSpec : Spek({

    describe("kotti-core") {

        it("should be true") {
            KotlinCoreImplementation.value shouldEqual true
        }

    }

})
