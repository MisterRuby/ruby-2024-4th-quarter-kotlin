package step09

fun main() {
    val immutableList = listOf("01", "02", "03", "04")

    val mutableList = mutableListOf("01", "02", "03", "04")
    mutableList.add("add String")

    for (index in mutableList.indices) {
        println("index = $index")
    }

    val lastIndex = mutableList.lastIndex

    val nonNullsList = listOfNotNull(2, 33, 12, null, 124, null, 111)
    println(nonNullsList)

    val list1 = listOf("one", "two", "three")
    val list2 = list1 + "four"                              // 기존 배열에 새 요소가 추가된 새로운 배열을 반환
    println(list2)                                          // ["one", "two", "three", "four"]
    println(list1 === list2)                                // false

    val list3 = list1 + listOf("four", "five", "six")       // 두 배열의 모든 요소가 병합된 새로운 배열을 반환
    println(list3)                                          // ["one", "two", "three", "four", "five", "six"]
    println(list1 === list3)                                // false

    val list4 = list1 - listOf("three", "four", "five")     // 일치하는 요소가 제거된 새로운 배열을 반환
    println(list4)                                          // ["one", "two"]

    val numList1 = listOf(1, 1, 2, 3, 4, 5)
    val numList2 = listOf(3, 3, 4, 5, 6, 7, 7)
    val unionList : Set<Int> = numList1.union(numList2)
    println(unionList)                                      // [1, 2, 3, 4, 5, 6, 7] 두 배열을 합친 결과에서 중복 요소들은 하나만 존재하는 새로운 배열을 반환

    val (part1, part2) = unionList.partition { it % 2 == 0 }
    println(part1)
    println(part2)
}

