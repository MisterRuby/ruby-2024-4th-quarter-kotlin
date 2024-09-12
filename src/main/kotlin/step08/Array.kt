package step08



fun main() {
    val arr = arrayOf(10, 11, 12, 123, 43, 31234, 16)

    arr.sort()                              // 원본 배열을 오름차순 정렬
    val sortedArr = arr.sortedArray()       // 오름차순으로 정렬된 새로운 배열을 생성하여 반환. 원본은 변경되지 않음
    val sortedList = arr.sorted()           // 오름차순으로 정렬된 새로운 List 를 반환. 원본은 변경되지 않음

    val strArr = arrayOf<String>("asdas", "asdasd", "asdasd", "12312", "fgfdsgasd", "as")
    strArr.sortBy { it.length }             // 문자열 길이를 기준으로 오름차순 정렬
}
