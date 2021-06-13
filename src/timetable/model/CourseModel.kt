package timetable.model

class CourseModel(
        val courseSort: String?,
        val courseName: String?,
        val classroomId: String?,
        val classroomName: String?,
        val weeks: String?,
        val dayOfWeek: String?,
        val timeOfDays: MutableList<Int>?,
        val groupName: String?
)