package changesort

import java.io.File
import java.util.regex.Pattern

object ChangeChineseSort {
    @JvmStatic
    fun main(args: Array<String>) {
        ChangeChineseSort.convert1()
    }

    private fun convert1() {
//        val folder = File("""\\jqorz.top\video\实习医生\涅瓦字幕组-实习医生1-100集""")
        val folder = File("""\\jqorz.top\video\实习医生\涅瓦字幕组-实习医生101-200集""")
        folder.listFiles()?.forEach {
            val oldName = it.name
            if (oldName.contains("第") && oldName.contains("集")) {
                val chineseNumber = oldName.substring(oldName.indexOf("第") + 1, oldName.indexOf("集"))
                var number = ConverterNumber.chineseToNumber(chineseNumber)
                if (number.length == 4 && number.startsWith("0")) {
                    number = number.substring(1, 4)
                }
                val newName = number + oldName
                println("oldName:$oldName newName:$newName")
                it.renameTo(File(folder.path, number + oldName))
            } else {
                val matcher = Pattern.compile("""E(\d+)""").matcher(oldName)
                if (matcher.find()) {
                    val number = matcher.group(1)
                    val newName = "${number}实习医生第${number}集中俄双文字幕版bynevagroup.mp4"
                    println("oldName:$oldName newName:$newName")
                    it.renameTo(File(folder.path, newName))
                }
            }

        }
    }

    private fun convert2() {
        val folder = File("""\\jqorz.top\video\实习医生\涅瓦字幕组-实习医生1-100集""")
        folder.listFiles()?.forEach {
            val oldName = it.name
            val matcher = Pattern.compile("""(\d+)(\D+)""").matcher(oldName)
            if (matcher.find()) {
                val number = matcher.group(1)
                val chineseNumber = matcher.group(2)
                val newName = "${number}实习医生第${chineseNumber}集中俄双文字幕版bynevagroup.mp4"
                println("oldName:$oldName newName:$newName")
                it.renameTo(File(folder.path, newName))
            }
        }
    }

}