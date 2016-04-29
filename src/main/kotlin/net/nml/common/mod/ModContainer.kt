package net.nml.common.mod

/**
 * Represents a mod container.
 *
 * @since 1.0-SNAPSHOT
 * @author PizzaCrust
 */
class ModContainer(name: String, version: String, author: String, website: String, mod: IMod) {
    /**
     * Core mod info values
     */
    val name: String
    val version: String
    val author: String
    val website: String

    /**
     * The implementation of the mod
     */
    val mod: IMod

    init {
        this.name = name
        this.version = version
        this.author = author
        this.website = website
        this.mod = mod
    }

    /**
     * Mods need to implement this interface to be registered to the mod loader.
     */
    interface IMod {
        /**
         * Called when the mod is enabled.
         */
        fun onEnable()

        /**
         * Called when the mod is disabled
         */
        fun onDisable()
    }

    /**
     * Mods are suggested to extend this class to receive a constructor for absolute no constructor issues.
     */
    abstract class AbstractMod() : IMod {
    }
}