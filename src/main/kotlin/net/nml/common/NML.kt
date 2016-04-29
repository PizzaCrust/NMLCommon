package net.nml.common

import net.nml.common.event.RegularEventBus
import net.nml.common.security.BasicSecurityManager

/**
 * The main class of NML Common.
 *
 * @since 1.0-SNAPSHOT
 * @author PizzaCrust
 */
class NML {
    /**
     * NML's avaliable objects as a class instance.
     */
    companion object Info {
        /**
         * The ASM security manager.
         */
        val securityManager : net.nml.common.security.SecurityManager = BasicSecurityManager()

        /**
         * The custom Kotlin event bus.
         */
        val eventBus : net.nml.common.event.EventBus = RegularEventBus()

        /**
         * Retrieves the common's version.
         */
        fun getCommonVersion() : String {
            return "1.0-SNAPSHOT"
        }

        /**
         * Represents branches of the versioning.
         */
        enum class Branch {
            /**
             * Stable, and shouldn't have any bugs.
             */
            STABLE,

            /**
             * In development, and should have bugs. Should only be used for development.
             */
            DEV,
        }

        /**
         * Retrieves the common's version branch enum.
         */
        fun getVersionBranch() : Branch {
            return Branch.DEV
        }

        /**
         * Commons has no implementation, show just output versioning info.
         */
        @JvmStatic fun main(args: Array<String>) {
            if (args.size > 0) {
                println("Arguments are not needed, why did you give arguments?")
                println("Versioning data is only available in commons.")
                val stringBuilder : StringBuilder = StringBuilder()
                for (arg in args) {
                    stringBuilder.append("'$arg', ")
                }
                println("Ignored these arguments: ${stringBuilder.toString()}")
            }
            val version = NML.getCommonVersion();
            val branchEnum = NML.getVersionBranch();
            var branchString = "STABLE";
            when (branchEnum) {
                Branch.DEV -> branchString = "DEV"
            }
            println("Commons is at $version.")
            println("Branch: $branchString")
        }
    }
}