package net.nml.common.security

/**
 * Represents an ASM security manager.
 *
 * @since 1.0-SNAPSHOT
 * @author PizzaCrust
 */
interface SecurityManager {

    /**
     * Checks the permission of the method call via enum.
     */
    fun checkPermission(permissionEnum: Permission) : Boolean

    /**
     * Represents permissions that the security manager handles.
     */
    enum class Permission {
        SHELL_EXECUTE,
        SHUTDOWN_HOOK
    }
}