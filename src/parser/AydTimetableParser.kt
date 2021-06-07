package parser

import utils.FileUtils

object AydTimetableParser {
    @JvmStatic
    fun main(args: Array<String>) {
        val html = FileUtils.readText("D:\\桌面\\安医大助手教务数据\\课表查询-2.txt")
        //<td>10</td>
        //<td>13001001</td><td>医学文献检索</td><td>1</td><td>		<a href="/eams/courseTableForStd!taskTable.action?lesson.id=199948" onclick="return bg.Go(this,null)" title="点击显示单个教学任务具体安排">(2020-2021-1)-13001001-2009500013-015</a>
        //</td><td>邹聪</td>
        val courseRegex = Regex("<td>(\\d*)</td>\\s*<td>(\\S*)</td>\\s*<td>(.+)</td>\\s*<td>((\\d)|(\\d.\\d))</td>\\s*<td>\\s*<a href=.*\\s.*\\s.*\\s.*>.*</a>\\s*</td>\\s*<td>(.*)</td>")
        val reg1 = Regex("(?i)<td>([^>]*)</td>")
        val reg2 = Regex(">([^>]*)</a>")
        val courseInfoList = arrayListOf<CourseInfo>()
        courseRegex.findAll(html).forEach {
            val courseSort = reg2.find(it.value)?.groups?.get(1)?.value
            val info = reg1.findAll(it.value).toMutableList()
            val courseId = info[1].groups[1]?.value
            val courseName = info[2].groups[1]?.value
            val credit = info[3].groups[1]?.value
            val teacherName = info[4].groups[1]?.value
            courseInfoList.add(CourseInfo(courseSort, courseId, courseName, credit, teacherName))
        }
        //TaskActivity(actTeacherId.join(','),actTeacherName.join(','),"1015((2020-2021-1)-04001110-1992500025-001)","医院管理学","176","1204(南)(翡翠路校区)","01111101111000000000000000000000000000000000000000000",null,"",assistantName,"","");
        //			index =2*unitCount+0;
        //			table0.activities[index][table0.activities[index].length]=activity;
        //			index =2*unitCount+1;
        //			table0.activities[index][table0.activities[index].length]=activity;
        //			index =2*unitCount+2;
        //			table0.activities[index][table0.activities[index].length]=activity;
        val timetableRegex = Regex("TaskActivity\\(actTeacherId.join\\(','\\),actTeacherName.join\\(','\\),\".*(\\(.*)\\)\",\"(.*)\",\"(.*)\",\"(.*)\",\"(.*)\",null,\"\",assistantName,\"\",\"(\\d)?\"\\);((?:\\s*index =\\d+\\*unitCount\\+\\d+;\\s*.*\\s)+)")
        //index =2*unitCount+0;
        val reg3 = Regex("index =(\\d+)\\*unitCount\\+(\\d+);")
        val timetableList = arrayListOf<TimetableModel>()
        timetableRegex.findAll(html).forEach {
            val courseSort = it.groups[1]?.value //(2020-2021-1)-04001110-1992500025-001
            val courseName = it.groups[2]?.value//医院管理学
            val classroomId = it.groups[3]?.value//176
            val classroomName = it.groups[4]?.value//1204(南)(翡翠路校区)
            val weeks = it.groups[5]?.value//01111101111000000000000000000000000000000000000000000
            val groupName = it.groups[6]?.value
            val info = it.groups[7]?.value
            if (info != null) {
                var dayOfWeek: String? = null
                val timeOfDays = arrayListOf<String>()
                reg3.findAll(info).forEach { each ->
                    dayOfWeek = each.groups[1]?.value
                    each.groups[2]?.value?.let { timeOfDay ->
                        timeOfDays.add(timeOfDay)
                    }
                }
                timetableList.add(TimetableModel(courseSort, courseName, classroomId, classroomName, weeks, dayOfWeek, timeOfDays, groupName))
            }
        }
        print(courseInfoList)
        print(timetableList)

    }

    class TimetableModel(
            val courseSort: String?,
            val courseName: String?,
            val classroomId: String?,
            val classroomName: String?,
            val weeks: String?,
            val dayOfWeek: String?,
            val timeOfDays: MutableList<String>?,
            val groupName: String?
    ) {
        override fun toString(): String {
            return "TimetableModel(courseSort=$courseSort, courseName=$courseName, classroomId=$classroomId, classroomName=$classroomName, weeks=$weeks, dayOfWeek=$dayOfWeek, timeOfDays=$timeOfDays, groupName=$groupName)"
        }
    }

    /**
     * CourseInfo(courseSort=(2020-2021-1)-04001110-1992500025-001, courseId=04001A23, courseName=医院管理学, credit=2, teacherName=肖锦铖)
     */
    class CourseInfo(
            val courseSort: String?,
            val courseId: String?,
            val courseName: String?,
            val credit: String?,
            val teacherName: String?
    ) {
        override fun toString(): String {
            return "CourseInfo(courseSort=$courseSort, courseId=$courseId, courseName=$courseName, credit=$credit, teacherName=$teacherName)"
        }
    }
}