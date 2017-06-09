package com.github.adeynack.kotti.swing

import java.awt.Component
import java.awt.FlowLayout
import javax.swing.Action
import javax.swing.JButton
import javax.swing.JPanel

/**
 * [JPanel] with an automatic layout of type [FlowLayout].
 *
 * Allows less verbose creation of such a panel.
 *
 *     val loginPanel = FlowPanel.left(txtUserName, txtPassword, btnLogin, vgap = 16)
 *
 * It also allows creation of button bars ([JButton]) from a list of [Action].
 *
 *     val buttonBar = FlowPanel.right(actionOK, actionCancel, actionHelp)
 *
 */
class FlowPanel(
    align: Int = defaultAlign,
    hgap: Int = defaultHGap,
    vgap: Int = defaultVGap,
    content: Iterable<Component> = emptyList()
) : JPanel(FlowLayout(align, hgap, vgap)) {

    companion object {

        // Default values taken from java.awt.FlowLayout.FlowLayout() (default constructor)
        private val defaultAlign = FlowLayout.CENTER
        private val defaultHGap = 5
        private val defaultVGap = 5

        fun left(content: List<Component>, hgap: Int = defaultHGap, vgap: Int = defaultVGap) = FlowPanel(FlowLayout.LEFT, hgap, vgap, content)
        fun center(content: List<Component>, hgap: Int = defaultHGap, vgap: Int = defaultVGap) = FlowPanel(FlowLayout.CENTER, hgap, vgap, content)
        fun right(content: List<Component>, hgap: Int = defaultHGap, vgap: Int = defaultVGap) = FlowPanel(FlowLayout.RIGHT, hgap, vgap, content)

        fun left(vararg content: Component, hgap: Int = defaultHGap, vgap: Int = defaultVGap) = FlowPanel(FlowLayout.LEFT, hgap, vgap, content.asIterable())
        fun center(vararg content: Component, hgap: Int = defaultHGap, vgap: Int = defaultVGap) = FlowPanel(FlowLayout.CENTER, hgap, vgap, content.asIterable())
        fun right(vararg content: Component, hgap: Int = defaultHGap, vgap: Int = defaultVGap) = FlowPanel(FlowLayout.RIGHT, hgap, vgap, content.asIterable())

        fun left(vararg actions: Action, hgap: Int = defaultHGap, vgap: Int = defaultVGap) = FlowPanel(FlowLayout.LEFT, hgap, vgap, actions.map(::JButton))
        fun center(vararg actions: Action, hgap: Int = defaultHGap, vgap: Int = defaultVGap) = FlowPanel(FlowLayout.CENTER, hgap, vgap, actions.map(::JButton))
        fun right(vararg actions: Action, hgap: Int = defaultHGap, vgap: Int = defaultVGap) = FlowPanel(FlowLayout.RIGHT, hgap, vgap, actions.map(::JButton))

    }

    init {
        content.forEach { add(it) }
    }

}
