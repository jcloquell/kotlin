/*
 * Copyright 2010-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the license/LICENSE.txt file.
 */

package kotlin.contracts

import kotlin.internal.ContractsDsl
import kotlin.internal.InlineOnly

/**
 * Signals that the annotated annotation class is a marker of an experimental contracts API.
 * Any declaration annotated with that marker can only be used with the compiler argument `-Xuse-experimental=kotlin.contracts.ExperimentalContracts`.
 */
@Retention(AnnotationRetention.BINARY)
@SinceKotlin("1.3")
@Experimental
public annotation class ExperimentalContracts

/**
 * This DSL used to specify effects of a contract.
 * Effect is a piece of knowledge about program context, it can be expression some function's behavior.
 * Contract DSL can be used only in the top-level functions.
 */
@ContractsDsl
@ExperimentalContracts
@SinceKotlin("1.3")
public interface ContractBuilder {
    /**
     * Expresses that function returned successfully.
     *
     * @sample samples.contracts.returnsContract
     * @sample samples.contracts.Contracts.Smartcasts.continueWithNotNull
     */
    @ContractsDsl public fun returns(): Returns

    /**
     * Expresses that function returned with some value.
     * Value can only be `true`, `false` or `null`.
     *
     * @sample samples.contracts.returnsTrueContract
     * @sample samples.contracts.returnsFalseContract
     * @sample samples.contracts.returnsNullContract
     *
     * @sample samples.contracts.Contracts.Smartcasts.continueWithInt
     * @sample samples.contracts.Contracts.Smartcasts.continueWithNotNullString
     * @sample samples.contracts.Contracts.Smartcasts.continueWithNotNullInt
     */
    @ContractsDsl public fun returns(value: Any?): Returns

    /**
     * Expresses that function returned with some not null value.
     *
     * @sample samples.contracts.returnsNotNullContract
     * @sample samples.contracts.Contracts.Smartcasts.continueWithNull
     */
    @ContractsDsl public fun returnsNotNull(): ReturnsNotNull

    /**
     * Expresses that:
     *  1) [lambda] will not be called after the call to owner-function is finished;
     *  2) [lambda] will not be passed to another function without the similar contract.
     * [kind] indicates that [lambda] is guaranteed to be invoked some statically known amount of times.
     *
     * Note that a function with the `callsInPlace` effect must be inline.
     *
     * @sample samples.contracts.Contracts.Initialization
     */
    @ContractsDsl public fun <R> callsInPlace(lambda: Function<R>, kind: InvocationKind = InvocationKind.UNKNOWN): CallsInPlace
}

/**
 * `InvocationKind` enum class is used to specify the amount of times, that `callable` which is used in the `callsInPlace` effect guaranteed will be invoked.
 */
@ContractsDsl
@ExperimentalContracts
@SinceKotlin("1.3")
public enum class InvocationKind {
    /**
     * Means that the `callable` will be invoked zero or one time.
     *
     * @sample samples.contracts.callsInPlaceAtMostOnceContract
     * @sample samples.contracts.Contracts.Initialization.valPossibleInitialization
     */
    @ContractsDsl AT_MOST_ONCE,

    /**
     * Means that the `callable` will be invoked one or more times.
     *
     * @sample samples.contracts.callsInPlaceAtLeastOnceContract
     * @sample samples.contracts.Contracts.Initialization.varInitializationOrReassignment
     */
    @ContractsDsl AT_LEAST_ONCE,

    /**
     * Means that the `callable` will be invoked exactly one time.
     *
     * @sample samples.contracts.callsInPlaceExactlyOnceContract
     * @sample samples.contracts.Contracts.Initialization.valInitialization
     */
    @ContractsDsl EXACTLY_ONCE,

    /**
     * Means that the `callable` will be invoked unknown amount of times.
     *
     * @sample samples.contracts.callsInPlaceUnknownContract
     * @sample samples.contracts.Contracts.Initialization.varPossibleInitialization
     */
    @ContractsDsl UNKNOWN
}

/**
 * The function to describe a contract.
 * The contract description must be at the beginning of a function and has at least one effect.
 *
 * @param builder the lambda in the body of which the effects from the `ContractBuilder` are specified.
 *
 * @sample samples.contracts.returnsContract
 * @sample samples.contracts.returnsTrueContract
 * @sample samples.contracts.returnsFalseContract
 * @sample samples.contracts.returnsNullContract
 * @sample samples.contracts.returnsNotNullContract
 * @sample samples.contracts.callsInPlaceAtMostOnceContract
 * @sample samples.contracts.callsInPlaceAtLeastOnceContract
 * @sample samples.contracts.callsInPlaceExactlyOnceContract
 * @sample samples.contracts.callsInPlaceUnknownContract
 */
@ContractsDsl
@ExperimentalContracts
@InlineOnly
@SinceKotlin("1.3")
@Suppress("UNUSED_PARAMETER")
public inline fun contract(builder: ContractBuilder.() -> Unit) { }