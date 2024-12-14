import scala.io.Source
import scala.util.Using

val data                                                 = scala.collection.mutable.HashMap[Int, List[String]]()
val lines                                                = Using(Source.fromResource("day05/input.txt"))(_.getLines().toList).get
val (initialState: List[String], commands: List[String]) = lines.splitAt(9)

def offsetOf(n: Int) = if (n == 1) n else n * 4 - 3

// Load the data
initialState.foreach { l =>
  (1 to (l.length / 4) + 1).foreach { c =>
    val payload = l(offsetOf(c)).toString.trim
    if (payload.nonEmpty) {
      val appended = data.getOrElse(c, List.empty).appended(payload)
      data.put(c, appended)
    }
  }
}

val pattern = """move (\d+) from (\d+) to (\d+)""".r

val mover9000: String => Unit = {
  case pattern(n, from, to) =>
    (1 to n.toInt).foreach { _ =>
      val moving = data(from.toInt).head
      data(from.toInt) = data(from.toInt).tail
      data(to.toInt) = moving :: data(to.toInt)
    }
  case _ => ()
}

val mover9001: String => Unit = {
  case pattern(n, from, to) =>
    val moving = data(from.toInt).take(n.toInt)
    data(from.toInt) = data(from.toInt).drop(n.toInt)
    data(to.toInt) = moving ++ data(to.toInt)
  case _ => ()
}

// Part 1
// commands.foreach(mover9000)
// val p1 = data.values.map(_.head).mkString

// Part 2
commands.foreach(mover9001)
val p2 = data.values.map(_.head).mkString
