import com.beust.klaxon.JsonObject
import com.beust.klaxon.JsonReader
import java.io.StringReader

fun main(args: Array<String>) {

    val objectString = """{
         "name" : "Joe",
         "age" : 23,
         "flag" : true,
         "array" : [1, 3],
         "obj1" : { "hello": {"a" : 1, "b" : 2 } }
    }"""

    JsonReader(StringReader(objectString)).use { reader ->
        reader.beginObject {
            var name: String? = null
            var age: Int? = null
            var flag: Boolean? = null
            var array: List<Any> = arrayListOf<Any>()
            var obj1: JsonObject? = null
            while (reader.hasNext()) {
                val readName = reader.nextName()
                println("key " + readName)
                when (readName) {
                    "name" -> name = reader.nextString()
                    "age" -> age = reader.nextInt()
                    "flag" -> flag = reader.nextBoolean()
                    "array" -> array = reader.nextArray()
                    "data" -> obj1 = reader.nextObject()
                    "obj1" -> obj1 = reader.nextObject()
                    else -> {
                        println("Unexpected name: $readName")
                    }
                }
            }
        }
    }
}