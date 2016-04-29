package net.nml.common.security

/**
 * Represents the security layers of NML.
 * This enum is according to the official NML specification document.
 * --> See the documentation to get knowledge about this.
 *
 * @since 1.0-SNAPSHOT
 * @author PizzaCrust
 */
enum class SLayer {
    SECURE,
    WARN,
    DOUBTFUL,
    BEWARE,
    BLEACH
}