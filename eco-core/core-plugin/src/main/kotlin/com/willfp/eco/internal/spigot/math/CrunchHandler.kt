package com.willfp.eco.internal.spigot.math

import com.willfp.eco.core.integrations.placeholder.PlaceholderManager
import org.bukkit.entity.Player
import redempt.crunch.CompiledExpression
import redempt.crunch.Crunch
import redempt.crunch.functional.EvaluationEnvironment

private val cache = mutableMapOf<String, CompiledExpression>()

fun evaluateExpression(expression: String, player: Player?): Double {
    val placeholderValues = PlaceholderManager.findPlaceholdersIn(expression)
        .map { PlaceholderManager.getResult(player, expression).toDouble() }
        .toDoubleArray()
    val compiled = generateExpression(expression)
    return compiled.evaluate(*placeholderValues)
}

private fun generateExpression(expression: String): CompiledExpression {
    val cached = cache[expression]

    if (cached != null) {
        return cached
    }

    val placeholders = PlaceholderManager.findPlaceholdersIn(expression)

    val env = EvaluationEnvironment()
    env.setVariableNames(*placeholders.toTypedArray())

    val compiled = Crunch.compileExpression(expression, env)
    cache[expression] = compiled
    return compiled
}
