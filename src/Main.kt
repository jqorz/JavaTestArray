import utils.GsonUtil

object Main {


    @JvmStatic
    fun main(args: Array<String>) {

        val models = mutableListOf(Fruit("aa1", "bb1"), Fruit("aa2", "bb2"))
        val maps = HashMap<String, String>()
        models.forEach {
            maps[it.aa] = it.bb
        }
        println(GsonUtil.jsonCreate(models))
        println(GsonUtil.jsonCreate(maps))
    }

    class Fruit(val aa: String, val bb: String)
}



