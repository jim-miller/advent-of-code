import scala.io.Source
import scala.util.Using

val UCASE_OFFSET = 38
val LCASE_OFFSET = 96

val sackList = Using(Source.fromResource("day03/input.txt"))(_.getLines().toList).get

val dupes = sackList
  .map(s => s.splitAt(s.length / 2) match { case (c1, c2) => c1.intersect(c2) })
  .map(_.head) // danger! not safe for production

def toPriorities(xs: List[Char]) = xs.map { c =>
  if (c.isUpper)
    c.toInt - UCASE_OFFSET
  else
    c.toInt - LCASE_OFFSET
}

toPriorities(dupes).sum

// Part 2
val badges = sackList
  .grouped(3)
  .map {
    case List(a, b, c) => a.intersect(b.intersect(c))
    case _             => throw new IllegalArgumentException()
  }
  .map(_.head) // danger! not safe for production
  .toList

toPriorities(badges).sum
