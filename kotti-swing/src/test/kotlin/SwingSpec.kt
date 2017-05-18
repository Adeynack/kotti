import com.github.adeynack.kotti.KotlinCoreImplementation
import com.github.adeynack.kotti.swing.KotlinSwingImplementation
import org.amshove.kluent.shouldEqual
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

object SwingSpec : Spek({

    describe("kotti-swing") {

        it("should be true") {
            KotlinSwingImplementation.value shouldEqual true
        }

        it("should be able to use things from kotti-core") {
            KotlinCoreImplementation.value shouldEqual true
        }

    }

})
