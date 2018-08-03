package temp.nrk.com.templet

import org.junit.Test

import org.junit.Assert.*
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
    @Test
    fun reverseTest(){
        println( reverse("Niroshan"))

        println(sort(listOf(9,5,0,3,4)))

    }

    fun reverse(text:String?):String?{
        if(text.isNullOrBlank() || text?.length==1){
            return text
        }
        return reverse(text!!.substring(1)) + text[0]

    }

    fun sort(list:List<Int>):List<Int>{
           Collections.reverse(list)
        return list
    }


}
