package net.nml.common.mod

import net.nml.common.security.InstructionScanner
import org.apache.commons.io.IOUtils
import org.objectweb.asm.ClassReader
import org.objectweb.asm.tree.ClassNode
import java.io.File
import java.io.InputStream
import java.net.URLClassLoader
import java.util.jar.JarFile

/**
 * The first loader and will be REPLACED in the next version.
 * Permission checking:
 *    [X] Main Class
 *    [ ] All mod classes
 * @since 1.0-SNAPSHOT
 * @author PizzaCrustss
 */
class BasicLoader : Loader {
    override fun load(file: File): ModContainer? {
        val jarFile: JarFile = JarFile(file)
        val mainClass: String = jarFile.manifest.mainAttributes.getValue("Main-Class")
        val isolatedClassLoader: URLClassLoader = URLClassLoader(arrayOf(file.toURI().toURL()))
        val mainClassObj : Class<*> = isolatedClassLoader.loadClass(mainClass)
        val mainClassInstance : ModContainer.IMod = mainClassObj.newInstance() as ModContainer.IMod
        val mainClassFilePath: String = "${mainClass.replace('.', '/')}.class"
        val inputStreamClass : InputStream = jarFile.getInputStream(jarFile.getJarEntry(mainClassFilePath))
        val classArray : ByteArray = IOUtils.toByteArray(inputStreamClass)
        val classReader = ClassReader(classArray)
        val classNode = ClassNode()
        classReader.accept(classNode, 0)
        for (method in classNode.methods) {
            val scanner = InstructionScanner(method)
            val violations: Int = scanner.scan()
            if (violations >= 11) {
                return null
            }
        }
        mainClassInstance.onEnable()
        val container: ModContainer = ModContainer(jarFile.manifest.mainAttributes.getValue("Mod-Name"),
                jarFile.manifest.mainAttributes.getValue("Mod-Version"),
                jarFile.manifest.mainAttributes.getValue("Mod-Author"),
                jarFile.manifest.mainAttributes.getValue("Mod-Website"),
                mainClassInstance)
        return container
    }
}