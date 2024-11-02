package step11

import kotlinx.coroutines.*

fun testLaunch() {
    // 새로운 코루틴을 백그라운드에 실행
    val job = GlobalScope.launch {
        // 코루틴 내부에서 사용되는 함수는 suspend() 로 선언된 지연 함수이어야 코루틴 기능을 사용할 수 있다.
        // delay 함수는 suspend 로 선언된 함수이다.
        delay(1000L)        // 1초의 지연
        println("testLaunch")       // 1초 뒤 출력
    }

    println("?????")                // 코루틴 내부의 지연과 상관없이 바로 실행됨
    Thread.sleep(2000L)
}

suspend fun doWork1(): String {
    println("start doWork1")
    delay(3000)
    println("end doWork1")
    return "doWork1"
}

suspend fun doWork2(): String {
    println("start doWork2")
    delay(2000)
    println("end doWork2")
    return "doWork2"
}

fun testSync() {
    GlobalScope.launch {
        // 순차적 실행
        doWork1()
        doWork2()     // doWork1() 이 완료될 때까지 대기
    }

    println("testSync")

    Thread.sleep(6000)
}

fun testAsync() {
    val one = GlobalScope.async {
        doWork1()
    }
    val two = GlobalScope.async {
        doWork2()
    }

    GlobalScope.launch {
        val combined = one.await() + "_" + two.await()
        println("text $combined")
    }

    println("testAsync")

    Thread.sleep(5000)
}

fun testCoroutineStart() {
    val job1 = GlobalScope.launch {
        // 바로 실행됨
        println("async 1")
    }


    val job2 = GlobalScope.launch(start = CoroutineStart.LAZY) {
        // start() 또는 await() 가 호출되는 시점에 실행됨
        println("async 2")
    }

    println("check point")

    Thread.sleep(3000)

    job2.start()    // 해당 시점에 실행됨
}

fun testNoneRunBlocking() {
    GlobalScope.launch {
        delay(1000L)
        println("test1")
    }

    println("test2")
}

fun testRunBlocking() {
    runBlocking {
        launch {
            delay(1000L)
            println("test1")
        }

        println("test2")
    }
}

fun testManyRunBlocking() {
    runBlocking {
        val jobs = List(100000) {
            launch {
                delay(1000L)
                println(".")
            }
        }

        // 여러 쓰레드에서 병렬 처리하는 것이 아닌 하나의 쓰레드에서 처리한다.
        // 작업 1에 대해 기다려야 한다면 쓰레드는 그동안 다른 작업2, 작업3...을 실행한다.
        jobs.forEach { it.join() }  // 모든 job 이 완료될 때까지 대기
    }
}

fun testFibonacciSeq() {
    val fibonacciSeq : Sequence<Int> = sequence {
        var a = 0
        var b = 1
        yield(1)

        while (true) {
            // yield : 실행을 잠시 멈추고 요소를 반환(산출)
            yield(a + b)
            val tmp = a + b
            a = b
            b = tmp
        }
    }

    // 시퀀스를 실행하여 yield 로 산출한 8개의 값을 반환하고 종료
    println(fibonacciSeq.take(8).toList())
}

fun testYieldAll() {
    val numSeq = sequence {
        // yield(1) ~ yield(100) 과 같다.
        yieldAll(1..100)
    }

    // yieldAll 을 통해 yield(1) ~ yield(11) 까지만 산출한다.
    println(numSeq.take(11).toList())
}

fun testNextSeq() {
    val numSeq = sequence {
        var a = 1
        while (true) {
            // yield : 실행을 잠시 멈추고 요소를 반환(산출)
            yield(a++)
        }
    }

    val iterator = numSeq.iterator()
    for (i in 1..10) {
        println(iterator.next())
    }
}

//fun main() = runBlocking<Unit> {
//    launch {
//        delay(1000L)
//        println("test1")
//    }
//
//    println("test2")
//}

fun testDispatcher() = runBlocking {
    val jobs = arrayListOf<Job>()
    jobs += launch(Dispatchers.Unconfined) {
        println("Unconfined : ${Thread.currentThread().name}")              // main
    }
    jobs += launch(coroutineContext) {
        // 부모의 문맥. 여기서는 runBlocking 의 문맥
        println("coroutineContext : ${Thread.currentThread().name}")        // main
    }
    jobs += launch(Dispatchers.Default) {
        // 부모의 문맥. 여기서는 runBlocking 의 문맥
        println("Default : ${Thread.currentThread().name}")                 // DefaultDispatcher-worker-2
    }
    jobs += launch(Dispatchers.IO) {
        // 입출력 중심의 문맥
        println("IO : ${Thread.currentThread().name}")                      // DefaultDispatcher-worker-2
    }
    jobs += launch {
        println("none param : ${Thread.currentThread().name}")              // main
    }
    jobs += launch(newSingleThreadContext("testThread")) {
        println("newSingleThreadContext : ${Thread.currentThread().name}")  // testThread
    }
}

fun main() {
//    delay(2000)
//    testLaunch()

//    testSync()

//    testAsync()

//    testCoroutineStart()

//    testNoneRunBlocking()
//    testRunBlocking()

//    val executionTime = measureTimeMillis {
//        testManyRunBlocking()
//    }
//    println("executionTime : $executionTime ms")

//    testFibonacciSeq()
//    testYieldAll()
//    testNextSeq()

    testDispatcher()
}



