/*
 * Copyright 2010-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the license/LICENSE.txt file.
 */

package kotlin.contracts

import kotlin.internal.ContractsDsl

@ContractsDsl
@ExperimentalContracts
@SinceKotlin("1.3")
public interface Effect

/**
 * `ConditionalEffect` corresponds to an additional condition applied to the [SimpleEffect].
 * `ConditionalEffect` can only be used in conjunction with [SimpleEffect].
 *
 * @see SimpleEffect.implies
 */
@ContractsDsl
@ExperimentalContracts
@SinceKotlin("1.3")
public interface ConditionalEffect : Effect

/**
 * `SimpleEffect` describes of function's behavior regardless of any conditions.
 */
@ContractsDsl
@ExperimentalContracts
@SinceKotlin("1.3")
public interface SimpleEffect {
    /**
     * `implies` allows to specify an additional condition for some `SimpleEffect`.
     *
     * @param booleanExpression the additional condition which is subset of Kotlin boolean expressions.
     *
     * Note: subset of Kotlin boolean expressions is true/false/null checks for function parameter or receiver, also allowed conjunction and disjunction.
     */
    @ContractsDsl
    @ExperimentalContracts
    public infix fun implies(booleanExpression: Boolean): ConditionalEffect
}

/**
 * `Returns` effect describes the return value of some function.
 * The return value can only be `true`, `false` or `null`.
 *
 * @see ContractBuilder.returns
 */
@ContractsDsl
@ExperimentalContracts
@SinceKotlin("1.3")
public interface Returns : SimpleEffect

/**
 * `ReturnsNotNull` effect describes that the return value of some function is not null.
 * The return value can only be `true`, `false` or `null`.
 *
 * @see ContractBuilder.returnsNotNull
 */
@ContractsDsl
@ExperimentalContracts
@SinceKotlin("1.3")
public interface ReturnsNotNull : SimpleEffect

/**
 * `CallsInPlace` effect describes of function's behavior in relation to the invocation of the received lambda.
 *
 * @see ContractBuilder.callsInPlace
 */
@ContractsDsl
@ExperimentalContracts
@SinceKotlin("1.3")
public interface CallsInPlace : SimpleEffect