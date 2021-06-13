package timetable.model

/**
 * @author  jqorz
 * @since  2021/6/8
 */
class TimetableModel(
        val courseSort: String,
        val courseName: String,
        val classroomName: String,
        val teacherName: String,
        val timeOfDay: Int,
        val dayOfWeek: Int,
        val week: Int,
        val credit: String,
        val courseCd: Int,
        val groupName: String
) {

    val showContent: String
        get() = "${courseName}${groupName}\n@${classroomName}"

    var backgroundColor: Int = 0

}