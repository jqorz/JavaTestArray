package pic

import utils.FileUtils
import java.io.File

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val folder = File("""""")
        val folder2 = File("""""")
        val allPics =
            FileUtils.listFilesInDirWithFilter(folder, { pathname -> pathname?.name?.endsWith(".png") == true }, true)
        allPics.forEach {
            val name = it.name
            val wantName =
                FileUtils.getFileNameNoExtension(name) + "_" + System.currentTimeMillis() + "." + FileUtils.getFileExtension(
                    name
                )
            FileUtils.copyFile(it, File(folder2, wantName))
        }
    }
}