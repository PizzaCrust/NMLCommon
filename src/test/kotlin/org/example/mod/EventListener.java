package org.example.mod;

import net.nml.common.event.Listener;
import net.nml.common.event.RegularEventBus;

/**
 * Listens to events.
 *
 * @since 1.0-SNAPSHOT
 */
public class EventListener {
    @Listener public void onDummyEvent(RegularEventBus.DummyEvent e) {
        System.out.println("Java interoperability!");
    }
}
