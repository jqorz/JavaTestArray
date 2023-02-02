package musicdeleterepeat

import java.io.File
import java.lang.Exception
import java.util.*

object Main {


    @JvmStatic
    fun main(args: Array<String>) {
        val dir = File("D:\\我的音乐")
        val children = dir.listFiles()
        val deleteFiles = ArrayList<File>()
        val processFileList = children?.filter { it.isFile && it.name.toLowerCase().endsWith("mp3") }
        processFileList!!.groupBy {
            it.name.replace(Regex(" \\(\\d+\\)"), "").replace(" ", "").replace(",","").toUpperCase().trim()
        }.forEach { (_, fileList) ->
            val newestOne = fileList.maxBy { it.lastModified() }!!
            val wantName = newestOne.name.replace(Regex(" \\(\\d+\\)"), "").trim()
            if (fileList.size > 1) {
                val delete = fileList - newestOne
                deleteFiles.addAll(delete)
                println("delete name=$wantName sum={${fileList.joinToString { it.name }}} delete=${delete.joinToString { it.name }}\n")
            }
        }
        println("total_delete size=${deleteFiles.size}")
        deleteFiles.forEach {
            it.delete()
        }
        Thread.sleep(1000)
        (processFileList - deleteFiles.toSet()).forEach { file ->
            val wantName = file.name.replace(Regex(" \\(\\d+\\)"), "").trim()
            if (!file.name.equals(wantName)) {
                println("rename oldName=${file.name} newName=${wantName}")
                val result = file.renameTo(File(file.parent + File.separator + wantName))
                if (!result) {
                    throw Exception("rename fail name=$wantName")
                }
            }
        }
    }

}



