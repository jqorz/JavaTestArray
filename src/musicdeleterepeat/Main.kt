package musicdeleterepeat

import java.io.File
import java.util.*

object Main {


    @JvmStatic
    fun main(args: Array<String>) {
        val dir = File("E:\\我的音乐")
        val children = dir.listFiles()
        val deleteFiles = ArrayList<File>()
        children.groupBy {
            it.name.replace(Regex(" \\(\\d+\\)"), "").toUpperCase().trim()
        }.forEach { t, u ->
            if (u.size > 1) {
                val delete = u - u.maxBy { it.lastModified() }!!
                deleteFiles.addAll(delete)
                System.out.println("name=$t sum={${u.joinToString { it.name }}} delete=${delete.joinToString { it.name }}\n")
            }
        }
        deleteFiles.forEach {
            it.delete()
        }
    }

}



