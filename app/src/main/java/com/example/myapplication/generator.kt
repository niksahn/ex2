import java.io.File
import kotlin.random.Random
val imagines= listOf("img","cat","longhaircat","nudecat","secondcat")
fun main(){
    var a:String
    val fileoutput = File("F:\\PROJ\\app\\src\\main\\java\\com\\example\\myapplication\\data.txt")
    fileoutput.writeText("")
    for (i in 0 until 100) {
        a= imagines[Random.nextInt(0, 4)]
        //fileoutput.appendText("kkkk")
        fileoutput.appendText( "ListItemData(\"title$i\",\"subtitle$i\",\"$a\"),\n")
    }
    //println("hhhhhhhhhhhhhh")
}