package org.example.mod;

import net.nml.common.NML;
import net.nml.common.mod.ModContainer;

/**
 * Connecting from Kotlin to Java. This example shows a mod using Java from Kotlin.
 * We will show these features in this mod:
 *  - Event Bus
 *  - Java Interoperability
 *
 * @since 1.0-SNAPSHOT
 */
public class CoreMod implements ModContainer.IMod{

    @Override public void onEnable() {
        System.out.println("Enable!");
        NML.Info.getEventBus().register(this);
    }

    @Override public void onDisable() {
        System.out.println("Disable!");
    }
}
