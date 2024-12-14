import scala.util.Using
import scala.io.Source

val input = Using(Source.fromResource("day10/ex.txt"))(_.mkString).get

val parseLine: String => Array[Int] =
  _.split(" ") match {
    case Array(_, n) =>
      Array(0, n.toInt)
    case _ =>
      Array(0)
  }

val transitions =
  for {
    row    <- input.split("\n")
    cycles <- parseLine(row)
  } yield cycles

val xRegState = transitions.scan(1)(_ + _)

val p1 = (20 to 220 by 40).map(n => n * xRegState(n - 1))

// Sprite falls within the current cursor within range (-1, 0, 1)
val p2 = {
  for {
    row <- xRegState.grouped(40)
    drawn = row.zipWithIndex.map { case (n, i) => if (Math.abs(n - i) <= 1) "#" else "." }
  } yield {
    drawn.mkString
  }
}.mkString("\n")

println(p2)
