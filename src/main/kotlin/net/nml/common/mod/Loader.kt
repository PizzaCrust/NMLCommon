package net.nml.common.mod

/**
 * Represents a mod loader.
 *
 * @since 1.0-SNAPSHOT
 * @author PizzaCrust
 */
interface Loader {
    /**
     * Loads a file as a mod.
     */
    fun load(file: java.io.File) : ModContainer?;
}