package hash

import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet

object MapSetTest {
    @JvmStatic
    fun main(args: Array<String>) {
        val map = HashMap<String, Any>()
        for (i in 0..9999) {
            map["" + i] = Any()
        }
        val set = HashSet<String>()
        for (i in 0..9999) {
            set.add("" + i)
        }

        val startTimeMap = System.nanoTime()
        map.forEach {
            val a = 1
        }
        println("map耗时 ${System.nanoTime() - startTimeMap}")

        val startTimeSet = System.nanoTime()
        set.forEach {
            val a = 1
        }
        println("set耗时 ${System.nanoTime() - startTimeSet}")
    }
}