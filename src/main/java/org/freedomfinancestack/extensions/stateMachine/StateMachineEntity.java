package org.freedomfinancestack.extensions.stateMachine;

public interface StateMachineEntity<E> {

    String EntityName();

    void SetState(State<E> name);

    State<E> GetState();
}
