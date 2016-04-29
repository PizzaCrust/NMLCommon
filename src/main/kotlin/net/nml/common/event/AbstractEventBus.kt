package net.nml.common.event

import java.util.*
import kotlin.reflect.KCallable
import kotlin.reflect.KClass
import kotlin.reflect.KParameter
import kotlin.reflect.jvm.javaType

/**
 * An abstract event bus.
 *
 * @since 1.0-SNAPSHOT
 * @author PizzaCrust
 */
class RegularEventBus : EventBus {
    val listeners: ArrayList<Any> = ArrayList<Any>()

    override fun register(instance: Any) {
        listeners.add(instance)
    }

    override fun call(eventInstance: EventBus.Event) {
        for (instance in listeners) {
            val theClass : KClass<Any> = instance.javaClass.kotlin
            val functions: Collection<KCallable<*>> = theClass.members
            for (function in functions) {
                if (hasFunctionAnnotation(function)) {
                    val parameters: List<KParameter> = function.parameters
                    for (parameter in parameters) {
                        if (parameter.type.javaType == eventInstance.javaClass) {
                            function.call(instance, eventInstance)
                        }
                    }
                }
            }
        }
    }

    fun hasFunctionAnnotation(callable: KCallable<*>) : Boolean {
        val annotations = callable.annotations
        for (annotation in annotations) {
            if (annotation is Listener) {
                return true
            }
        }
        return false
    }

    /**
     * Represents a dummy event used for dummy listener testing.
     */
    class DummyEvent : EventBus.Event {}

    /**
     * A dummy model of a event listener.
     */
    class DummyEventListener {
        /**
         * Called when the dummy event is called.
         */
        @Listener fun onDummyEvent(e: DummyEvent) {
            println("Event bus works!")
        }
    }

    companion object Tests{
        @JvmStatic fun main(args: Array<String>) {
            val regularEventBus : EventBus = RegularEventBus()
            regularEventBus.register(DummyEventListener())
            regularEventBus.call(DummyEvent())
        }
    }

}