package net.nml.common.event

import net.nml.common.mod.ModContainer

/**
 * Contains all the classes of the default events.
 *
 * @since 1.0-SNAPSHOT
 * @author PizzaCrust
 */
class DefaultEvents {

    // TODO: Work on this event, after mod loading.
    class NMLLoadingModEvent(container: ModContainer) : EventBus.Event {
        val container : ModContainer;

        init {
            this.container = container
        }
    }
}