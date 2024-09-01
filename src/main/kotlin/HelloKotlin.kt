import kotlin.math.PI
import kotlin.math.abs

/**
 * Package 선언이 없을 경우 해당 파일은 default 패키지에 포함된다.
 * - default 패키지 : src 폴더에 따로 지정되지 않은 패키지
 *
 * 코틀린은 main 함수가 있는 파일 이름을 기준으로 자바 클래스가 자동 생성됨
 * - 생성된 자바 클래시는 Tools -> Kotlin -> Show Kotlin -> Bytecode 후 Decompile 을 통해 확인 가능
 */
fun main(args: Array<String>) {
    println("Hello Kotlin!")

    // kotlin.math 는 코틀린의 기본 패키지에 포함되어 있지 않으므로 해당 패키지를 import 해야 한다.
    // 코틀린 기본 패키지 : 코틀린으로 프로그램을 만들 때 자주 사용되는 클래스와 함수 등을 미리 만들어 놓은 것
    println(PI)
    println(abs(-12))

//    var str1: String = null       기본적으로 변수에 null 값 할당을 허용하지 않음
    var str2: String? = null

//    val a: Int = 10L              코틀린은 변수에 값을 할당/재할당 시 자동 형 변환을 허용하지 않음
    val result = 1L + 3             // 서로 다른 값을 연산할 시에는 범위가 큰 타입으로 자동 형 변환하여 연산

    val a: Int = 128
    val b: Int = 128
    val c: Int? = a

    print(a == b)
    print(a === b)
    print(b == c)
    print(b === c)

    val str: Any
    str = "ruby"
    if (str is String) print(str.length)    // is 키워드로 자료형을 검사하여 true 일 경우 해당 자료형으로 스마트 캐스트 된다.

    var t: Any = 1
    t = 20L
    t = "ruby"
}
