package step05

open class Target

interface OrderInfo {
    fun setInfo()
}

class Region : Target(), OrderInfo {
    override fun setInfo() {
        TODO("Not yet implemented")
    }
}

class Channel : Target()
