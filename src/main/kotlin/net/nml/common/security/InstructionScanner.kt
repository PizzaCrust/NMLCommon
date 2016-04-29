package net.nml.common.security

import net.nml.common.NML
import org.objectweb.asm.tree.AbstractInsnNode
import org.objectweb.asm.tree.MethodInsnNode
import org.objectweb.asm.tree.MethodNode

/**
 * Uses ASM to enforce the SecurityManager.
 *
 * @since 1.0-SNAPSHOT
 * @author PizzaCrust
 */
class InstructionScanner(node: MethodNode) {
    val mNode: MethodNode;

    init {
        mNode = node
    }

    fun scan() : Int {
        val iterator: Iterator<AbstractInsnNode> = mNode.instructions.iterator()
        var violationCount = 0
        while (iterator.hasNext()) {
            var currentInsn = iterator.next();
            if (currentInsn is MethodInsnNode) {
                val methodInsnNode : MethodInsnNode = currentInsn
                var hasPermission : Boolean = true;
                if (methodInsnNode.name.equals("exec") && methodInsnNode.owner.equals(Runtime::class.java.name.replace('.', '/'))) {
                    hasPermission = NML.securityManager.checkPermission(SecurityManager.Permission.SHELL_EXECUTE)
                }
                if (methodInsnNode.name.equals("start") && methodInsnNode.owner.equals(ProcessBuilder::class.java.name.replace('.', '/'))) {
                    hasPermission = NML.securityManager.checkPermission(SecurityManager.Permission.SHELL_EXECUTE)
                }
                if (methodInsnNode.name.equals("addShutdownHook") && methodInsnNode.owner.equals(Runtime::class.java.name.replace('.', '/'))) {
                    hasPermission = NML.securityManager.checkPermission(SecurityManager.Permission.SHUTDOWN_HOOK)
                }
                if (!hasPermission) {
                    violationCount++
                }
            }
        }
        return violationCount
    }
}