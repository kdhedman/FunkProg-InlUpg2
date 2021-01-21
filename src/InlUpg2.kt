import java.io.File

fun main() {
    val input = File("src/input.txt").readLines()
    val tomteMap = hashMapOf<String, List<String>>()

    input.map {
        for((index, value) in it.split(" ").withIndex()){
            if(index == 1)
                tomteMap.put(
                    it.split(" ")[0],
                    it.split(" ").filterIndexed({index, _ -> index>0 }).toList()
                )
        }
    }
    while(true){
        println("Skriv namnet på en tomte för att se underordnade.")
        val input = readLine()!!
        if(input == "0") break
        else println(listSubordinates(tomteMap, input).joinToString(separator = ", "))
    }
}

fun listSubordinates(hashMap: Map<String, List<String>>,entry: String) : MutableList<String> {
    if (hashMap.containsKey(entry)) {
        val result = mutableListOf<String>()
        for(tomte in hashMap[entry] ?: error("")){
            result += tomte
            result += listSubordinates(hashMap, tomte)
        }
        return result
    } else {
        return mutableListOf()
    }
}