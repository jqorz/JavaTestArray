package musicdeleterepeat

import java.io.File
import java.lang.Exception
import java.util.*

object Main {


    @JvmStatic
    fun main(args: Array<String>) {
        deleteMultiInFolder()
//        deleteRepeat()
    }


    private fun deleteMultiInFolder() {
        val dir = File("D:\\我的音乐")
        val children = dir.listFiles()
        val deleteFiles = ArrayList<File>()
        children?.forEach {
            if (it.name.endsWith(".MP3")) {
                it.renameTo(File(it.name.replace(".MP3", ".mp3")))
            }
        }
        val processFileList = children?.filter { it.isFile && it.name.endsWith("mp3") }
        processFileList!!.groupBy {
            val groupKey =
                it.nameWithoutExtension.replace(Regex("\\(\\d+\\)"), "").replace(" ", "").replace(",", "").toUpperCase()
                    .trim()
            groupKey
        }.forEach { (_, fileList) ->
            val newestOne = fileList.maxBy { it.lastModified() }!!
            val wantName = newestOne.name.replace(Regex("\\(\\d+\\)"), "").trim()
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


    private fun deleteRepeat() {
        val dir1 = File("D:\\我的音乐")
        val dir2 = File("D:\\我的音乐2")
        val mp3Files = dir1.listFiles() ?: return
        val ncmFiles = dir2.listFiles() ?: return
        val wantDeleteList = arrayListOf<File>()

        ncmFiles.forEach { file ->
            var contain = false
            mp3Files.forEach {
                if (it.nameWithoutExtension.toLowerCase().replace(",", " ") == file.nameWithoutExtension.toLowerCase()
                    || it.nameWithoutExtension.toLowerCase().replace(Regex("(\\S+),(.*)-"), "\$1 \$2")
                    == file.nameWithoutExtension.toLowerCase()
                ) {
                    wantDeleteList.add(it)
                    contain = true
                }
            }
            if (!contain) {
                println("Can not checked file is ${file.nameWithoutExtension}")
            }
        }
        println("================================================")
        println("Sum want delete count is ${wantDeleteList.size} , dir2's file count is ${ncmFiles.size}")
        wantDeleteList.forEach {
            it.deleteOnExit()
        }
        println("Delete finished")
    }
}



