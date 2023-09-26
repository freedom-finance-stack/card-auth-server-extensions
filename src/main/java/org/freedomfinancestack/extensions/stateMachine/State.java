package org.freedomfinancestack.extensions.stateMachine;

public interface State<E> {
    State<E> nextState(E event) throws InvalidStateTransactionException;
}
