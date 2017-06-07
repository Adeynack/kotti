package com.github.adeynack.kotti.swing

import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldEqual
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import java.awt.Component
import java.awt.FlowLayout
import java.awt.event.ActionEvent
import javax.swing.AbstractAction
import javax.swing.JButton
import javax.swing.JTextField

object FlowPanelSpec : Spek({

    describe("FlowPanel") {

        val buttonOK = JButton("OK")
        val buttonCancel = JButton("Cancel")
        val textInput = JTextField("foo")

        val content: List<Component> = listOf(textInput, buttonCancel, buttonOK)

        context("Using default constructor") {

            on("using it without parameters") {
                val p = FlowPanel()
                it("has a FlowLayout") { p.layout shouldBeInstanceOf FlowLayout::class.java }
                val layout = p.layout as FlowLayout
                it("should be centered") { layout.alignment shouldEqual FlowLayout.CENTER }
                it("should have a horizontal gap of 5") { layout.hgap shouldEqual 5 }
                it("should have a vertical gap of 5") { layout.vgap shouldEqual 5 }
                it("should have no component") { p.componentCount shouldEqual 0 }
            }

        }

        context("using factory methods receiving a list of components") {

            on("calling `left`") {
                val p = FlowPanel.left(content)
                it("has a FlowLayout") { p.layout shouldBeInstanceOf FlowLayout::class.java }
                val layout = p.layout as FlowLayout
                it("should be left aligned") { layout.alignment shouldEqual FlowLayout.LEFT }
                it("should have a horizontal gap of 5") { layout.hgap shouldEqual 5 }
                it("should have a vertical gap of 5") { layout.vgap shouldEqual 5 }
                it("should have the 3 expected components") { p.components.toList() shouldEqual content }
            }
            on("calling `left` specifying gaps") {
                val p = FlowPanel.left(content, 10, 12)
                it("has a FlowLayout") { p.layout shouldBeInstanceOf FlowLayout::class.java }
                val layout = p.layout as FlowLayout
                it("should be left aligned") { layout.alignment shouldEqual FlowLayout.LEFT }
                it("should have a horizontal gap of 10") { layout.hgap shouldEqual 10 }
                it("should have a vertical gap of 12") { layout.vgap shouldEqual 12 }
                it("should have the 3 expected components") { p.components.toList() shouldEqual content }
            }

            on("calling `center`") {
                val p = FlowPanel.center(content)
                it("has a FlowLayout") { p.layout shouldBeInstanceOf FlowLayout::class.java }
                val layout = p.layout as FlowLayout
                it("should be center aligned") { layout.alignment shouldEqual FlowLayout.CENTER }
                it("should have a horizontal gap of 5") { layout.hgap shouldEqual 5 }
                it("should have a vertical gap of 5") { layout.vgap shouldEqual 5 }
                it("should have the 3 expected components") { p.components.toList() shouldEqual content }
            }
            on("calling `center` specifying gaps") {
                val p = FlowPanel.center(content, 10, 12)
                it("has a FlowLayout") { p.layout shouldBeInstanceOf FlowLayout::class.java }
                val layout = p.layout as FlowLayout
                it("should be center aligned") { layout.alignment shouldEqual FlowLayout.CENTER }
                it("should have a horizontal gap of 10") { layout.hgap shouldEqual 10 }
                it("should have a vertical gap of 12") { layout.vgap shouldEqual 12 }
                it("should have the 3 expected components") { p.components.toList() shouldEqual content }
            }

            on("calling `right`") {
                val p = FlowPanel.right(content)
                it("has a FlowLayout") { p.layout shouldBeInstanceOf FlowLayout::class.java }
                val layout = p.layout as FlowLayout
                it("should be right aligned") { layout.alignment shouldEqual FlowLayout.RIGHT }
                it("should have a horizontal gap of 5") { layout.hgap shouldEqual 5 }
                it("should have a vertical gap of 5") { layout.vgap shouldEqual 5 }
                it("should have the 3 expected components") { p.components.toList() shouldEqual content }
            }
            on("calling `right` specifying gaps") {
                val p = FlowPanel.right(content, 10, 12)
                it("has a FlowLayout") { p.layout shouldBeInstanceOf FlowLayout::class.java }
                val layout = p.layout as FlowLayout
                it("should be right aligned") { layout.alignment shouldEqual FlowLayout.RIGHT }
                it("should have a horizontal gap of 10") { layout.hgap shouldEqual 10 }
                it("should have a vertical gap of 12") { layout.vgap shouldEqual 12 }
                it("should have the 3 expected components") { p.components.toList() shouldEqual content }
            }
        }

        context("using factory methods receiving a variable list of component arguments") {

            on("calling `left`") {
                val p = FlowPanel.left(textInput, buttonCancel, buttonOK)
                it("has a FlowLayout") { p.layout shouldBeInstanceOf FlowLayout::class.java }
                val layout = p.layout as FlowLayout
                it("should be left aligned") { layout.alignment shouldEqual FlowLayout.LEFT }
                it("should have a horizontal gap of 5") { layout.hgap shouldEqual 5 }
                it("should have a vertical gap of 5") { layout.vgap shouldEqual 5 }
                it("should have the 3 expected components") { p.components.toList() shouldEqual content }
            }
            on("calling `left` specifying gaps") {
                val p = FlowPanel.left(textInput, buttonCancel, buttonOK, hgap = 10, vgap = 12)
                it("has a FlowLayout") { p.layout shouldBeInstanceOf FlowLayout::class.java }
                val layout = p.layout as FlowLayout
                it("should be left aligned") { layout.alignment shouldEqual FlowLayout.LEFT }
                it("should have a horizontal gap of 10") { layout.hgap shouldEqual 10 }
                it("should have a vertical gap of 12") { layout.vgap shouldEqual 12 }
                it("should have the 3 expected components") { p.components.toList() shouldEqual content }
            }

            on("calling `center`") {
                val p = FlowPanel.center(textInput, buttonCancel, buttonOK)
                it("has a FlowLayout") { p.layout shouldBeInstanceOf FlowLayout::class.java }
                val layout = p.layout as FlowLayout
                it("should be center aligned") { layout.alignment shouldEqual FlowLayout.CENTER }
                it("should have a horizontal gap of 5") { layout.hgap shouldEqual 5 }
                it("should have a vertical gap of 5") { layout.vgap shouldEqual 5 }
                it("should have the 3 expected components") { p.components.toList() shouldEqual content }
            }
            on("calling `center` specifying gaps") {
                val p = FlowPanel.center(textInput, buttonCancel, buttonOK, hgap = 10, vgap = 12)
                it("has a FlowLayout") { p.layout shouldBeInstanceOf FlowLayout::class.java }
                val layout = p.layout as FlowLayout
                it("should be center aligned") { layout.alignment shouldEqual FlowLayout.CENTER }
                it("should have a horizontal gap of 10") { layout.hgap shouldEqual 10 }
                it("should have a vertical gap of 12") { layout.vgap shouldEqual 12 }
                it("should have the 3 expected components") { p.components.toList() shouldEqual content }
            }

            on("calling `right`") {
                val p = FlowPanel.right(textInput, buttonCancel, buttonOK)
                it("has a FlowLayout") { p.layout shouldBeInstanceOf FlowLayout::class.java }
                val layout = p.layout as FlowLayout
                it("should be right aligned") { layout.alignment shouldEqual FlowLayout.RIGHT }
                it("should have a horizontal gap of 5") { layout.hgap shouldEqual 5 }
                it("should have a vertical gap of 5") { layout.vgap shouldEqual 5 }
                it("should have the 3 expected components") { p.components.toList() shouldEqual content }
            }
            on("calling `right` specifying gaps") {
                val p = FlowPanel.right(textInput, buttonCancel, buttonOK, hgap = 10, vgap = 12)
                it("has a FlowLayout") { p.layout shouldBeInstanceOf FlowLayout::class.java }
                val layout = p.layout as FlowLayout
                it("should be right aligned") { layout.alignment shouldEqual FlowLayout.RIGHT }
                it("should have a horizontal gap of 10") { layout.hgap shouldEqual 10 }
                it("should have a vertical gap of 12") { layout.vgap shouldEqual 12 }
                it("should have the 3 expected components") { p.components.toList() shouldEqual content }
            }
        }

        context("using factory methods receiving a variable list of actions") {

            val actionFoo = object : AbstractAction("Foo") {
                override fun actionPerformed(e: ActionEvent?) {}
            }
            val actionBar = object : AbstractAction("Bar") {
                override fun actionPerformed(e: ActionEvent?) {}
            }

            fun checkButtons(components: Array<Component>) {
                components.size shouldEqual 2
                components[0] shouldBeInstanceOf JButton::class.java
                (components[0] as JButton).text shouldEqual "Foo"
                (components[0] as JButton).action shouldEqual actionFoo
                components[1] shouldBeInstanceOf JButton::class.java
                (components[1] as JButton).text shouldEqual "Bar"
                (components[1] as JButton).action shouldEqual actionBar
            }

            on("calling `left`") {
                val p = FlowPanel.left(actionFoo, actionBar)
                it("has a FlowLayout") { p.layout shouldBeInstanceOf FlowLayout::class.java }
                val layout = p.layout as FlowLayout
                it("should be left aligned") { layout.alignment shouldEqual FlowLayout.LEFT }
                it("should have a horizontal gap of 5") { layout.hgap shouldEqual 5 }
                it("should have a vertical gap of 5") { layout.vgap shouldEqual 5 }
                it("should have the 2 expected buttons") { checkButtons(p.components) }
            }
            on("calling `left` specifying gaps") {
                val p = FlowPanel.left(actionFoo, actionBar, hgap = 10, vgap = 12)
                it("has a FlowLayout") { p.layout shouldBeInstanceOf FlowLayout::class.java }
                val layout = p.layout as FlowLayout
                it("should be left aligned") { layout.alignment shouldEqual FlowLayout.LEFT }
                it("should have a horizontal gap of 10") { layout.hgap shouldEqual 10 }
                it("should have a vertical gap of 12") { layout.vgap shouldEqual 12 }
                it("should have the 2 expected buttons") { checkButtons(p.components) }
            }

            on("calling `center`") {
                val p = FlowPanel.center(actionFoo, actionBar)
                it("has a FlowLayout") { p.layout shouldBeInstanceOf FlowLayout::class.java }
                val layout = p.layout as FlowLayout
                it("should be center aligned") { layout.alignment shouldEqual FlowLayout.CENTER }
                it("should have a horizontal gap of 5") { layout.hgap shouldEqual 5 }
                it("should have a vertical gap of 5") { layout.vgap shouldEqual 5 }
                it("should have the 2 expected buttons") { checkButtons(p.components) }
            }
            on("calling `center` specifying gaps") {
                val p = FlowPanel.center(actionFoo, actionBar, hgap = 10, vgap = 12)
                it("has a FlowLayout") { p.layout shouldBeInstanceOf FlowLayout::class.java }
                val layout = p.layout as FlowLayout
                it("should be center aligned") { layout.alignment shouldEqual FlowLayout.CENTER }
                it("should have a horizontal gap of 10") { layout.hgap shouldEqual 10 }
                it("should have a vertical gap of 12") { layout.vgap shouldEqual 12 }
                it("should have the 2 expected buttons") { checkButtons(p.components) }
            }

            on("calling `right`") {
                val p = FlowPanel.right(actionFoo, actionBar)
                it("has a FlowLayout") { p.layout shouldBeInstanceOf FlowLayout::class.java }
                val layout = p.layout as FlowLayout
                it("should be right aligned") { layout.alignment shouldEqual FlowLayout.RIGHT }
                it("should have a horizontal gap of 5") { layout.hgap shouldEqual 5 }
                it("should have a vertical gap of 5") { layout.vgap shouldEqual 5 }
                it("should have the 2 expected buttons") { checkButtons(p.components) }
            }
            on("calling `right` specifying gaps") {
                val p = FlowPanel.right(actionFoo, actionBar, hgap = 10, vgap = 12)
                it("has a FlowLayout") { p.layout shouldBeInstanceOf FlowLayout::class.java }
                val layout = p.layout as FlowLayout
                it("should be right aligned") { layout.alignment shouldEqual FlowLayout.RIGHT }
                it("should have a horizontal gap of 10") { layout.hgap shouldEqual 10 }
                it("should have a vertical gap of 12") { layout.vgap shouldEqual 12 }
                it("should have the 2 expected buttons") { checkButtons(p.components) }
            }
        }

    }

})
