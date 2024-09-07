package step06

class Material(var id: Long, var name: String, var time: Int, _description: String) {
    var description: String = _description
        set(value) {
            // 내부적으로 this.setDescription(value) 를 호출하므로 무한 참조가 발생한다.
//            description = value
            // field : 보조 필드. 프로퍼티를 참조하는 변수
            field = value
        }
}

class Person {
    lateinit var name: String
}

class UserInfo {
    val name by lazy {
        println("lazy init name")
        "ruby"      // lazy 반환값. name
    }

    fun printName() {
        println("name is $name")
        println("name is $name")
    }
}

interface Animal {
    fun eat()
}

class Cat : Animal {
    override fun eat() {
        println("cat eat")
    }
}
val cat = Cat()
class Robot : Animal by cat

class Phone(val name: String, val cost: Int) {
    companion object {
        var manufacturer: String = "apple"
        var origin: String = "usa"
    }
}

fun main(args: Array<String>) {
    val material = Material(id = 1, name = "광고소재1", time = 30, "광고소재")

    // 프로퍼티로 직접 접근하는 것이 아닌, 내장되어 있는 접근 메서드를 통해 접근
    val materialName = material.name
    material.time = 15

    val person = Person()
//    println(person.name)        // 초기화 되지 않은 값을 사용하기 때문에 예외 발생

    val userInfo = UserInfo()
    userInfo.printName()

    val lazyUserInfo : UserInfo by lazy {
        println("lazy init userInfo")
        UserInfo()
    }

    println("not init userInfo")
    lazyUserInfo.printName()

    val robot = Robot()
    robot.eat()
    println(robot is Animal)
}
