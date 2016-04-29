package net.nml.common.event

/**
 * Represents an event bus.
 *
 * @since 1.0-SNAPSHOT
 * @author PizzaCrust
 */
interface EventBus {
    /**
     * Registers a event listener.
     */
    fun register(instance: Any)

    /**
     * A marker interface that represents an event class.
     */
    interface Event {}

    /**
     * Calls a event on the event bus.
     */
    fun call(eventInstance: Event)
}