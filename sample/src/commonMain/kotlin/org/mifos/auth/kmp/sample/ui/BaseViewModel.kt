/*
 * Copyright 2026 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 */
package org.mifos.auth.kmp.sample.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<S, E, A>(
    initialState: S,
) : ViewModel() {

    protected val mutableStateFlow: MutableStateFlow<S> = MutableStateFlow(initialState)

    private val eventChannel: Channel<E> = Channel(capacity = Channel.Factory.UNLIMITED)

    private val internalActionChannel = Channel<A>(capacity = Channel.Factory.UNLIMITED)

    protected val state: MutableStateFlow<S>
        get() = mutableStateFlow

    val stateFlow: StateFlow<S> = mutableStateFlow.asStateFlow()

    val eventFlow: Flow<E> = eventChannel.receiveAsFlow()

    val actionChannel: SendChannel<A> = internalActionChannel

    init {
        viewModelScope.launch {
            internalActionChannel
                .consumeAsFlow()
                .collect { action ->
                    handleAction(action)
                }
        }
    }

    protected abstract fun handleAction(action: A)

    fun trySendAction(action: A) {
        actionChannel.trySend(action)
    }

    protected fun sendEvent(event: E) {
        viewModelScope.launch { eventChannel.send(event) }
    }
}
