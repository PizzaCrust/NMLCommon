package net.nml.common.security

/**
 * The security manager placed in the first versions of NML.
 * This will be replaced in the future with a more customizable version.
 *
 * @since 1.0-SNAPSHOT
 * @author PizzaCrust
 */
class BasicSecurityManager : SecurityManager {
    override fun checkPermission(permissionEnum: SecurityManager.Permission): Boolean {
        return false
    }
}