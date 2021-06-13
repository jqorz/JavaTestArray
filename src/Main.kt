import utils.GsonUtil
import java.util.*

object Main {


    @JvmStatic
    fun main(args: Array<String>) {

//        val models = mutableListOf(Fruit("aa1", "bb1"), Fruit("aa2", "bb2"))
//        val maps = HashMap<String, String>()
//        models.forEach {
//            maps[it.aa] = it.bb
//        }
//        println(GsonUtil.jsonCreate(models))
//        println(GsonUtil.jsonCreate(maps))

        val data = arrayListOf(1, 2, 3, 5, 6, 8, 9, 10)
        print(GsonUtil.jsonCreate(groupByConsequent(data)))
    }

    private fun groupByConsequent(list: MutableList<Int>): List<MutableList<Int>> {
        val stackList = arrayListOf<Stack<Int>>()
        var stack = Stack<Int>()
        stackList.add(stack)

        list.forEach { item ->
            if (stack.isEmpty()) {
                stack.push(item)
            } else {
                if ((item - 1) != stackList[stackList.size - 1].peek()) {
                    stack = Stack()
                    stack.push(item)
                    stackList.add(stack)
                } else {
                    stackList[stackList.size - 1].push(item)
                }
            }
        }

        return stackList.map { it.toMutableList() }

    }

    class Fruit(val aa: String, val bb: String)
}



