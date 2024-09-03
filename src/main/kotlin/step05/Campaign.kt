package step05

class Campaign (var id: Long, var name: String, var cost: Int = 1000) {
    init {
        println("-----초기화 시작")
        println("$id / $name / $cost")
        println("-----초기화 종료")
    }
}
