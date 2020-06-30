import java.util.concurrent.TimeUnit
import java.util.concurrent.CountDownLatch
import java.io.File

object Threads {
    @kotlin.jvm.Throws(InterruptedException::class)
    @kotlin.jvm.JvmStatic
    fun main(args: Array<String>) {
        val threads = 10
        val latch = CountDownLatch(threads)
        for (i in 0 until threads) {
            val thread: java.lang.Thread = java.lang.Thread(ThreadProgram(i, latch))
            thread.start()
            Time.sleep(100, TimeUnit.MILLISECONDS)
        }
        println("I am not finished")
        latch.await()
        println("I am finished")
    }

    internal class ThreadProgram(private val id: Int, latch: CountDownLatch) : Runnable {
        private val latch: CountDownLatch
        override fun run() {
            Time.sleep(RandomTools.nextInt(1, 5), TimeUnit.SECONDS)
            println("I am $id")
            latch.countDown()
        }

        init {
            this.latch = latch
        }
    }
}