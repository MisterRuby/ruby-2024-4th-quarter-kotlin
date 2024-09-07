package step05

fun main(args: Array<String>) {
    val campaign1 = Campaign(id = 1, name = "광고계약1", cost = 10000)
    // 프로퍼티의 초기값이 지정되어 있을 경우 생성자 호출시 생략할 수 있다
    val campaign2 = Campaign(id = 2, name = "광고계약2")
}
