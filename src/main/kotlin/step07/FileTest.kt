package step07

import java.io.FileNotFoundException

class FileTest {
    @Throws(FileNotFoundException::class)
    fun throwTest(): Boolean {
        throw NumberFormatException("test")
    }
}
