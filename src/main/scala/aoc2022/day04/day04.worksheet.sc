import scala.io.Source
import scala.util.Using

val numPattern = """^(\d+)-(\d+),(\d+)-(\d+)""".r

numPattern.unapplySeq("2-3,3-5")
numPattern.unapplySeq("12-3,33-55")

val lines      = Using(Source.fromResource("day04/input.txt"))(_.getLines().toList).get

// Part 1
lines
  .map {
    case numPattern(xL, xU, yL, yU) =>
      val rangeA = (xL.toInt to xU.toInt)
      val rangeB = (yL.toInt to yU.toInt)
      rangeA.forall(rangeB.contains) | rangeB.forall(rangeA.contains)
    case l => throw new IllegalArgumentException(s"unable to parse: $l")
  }
  .count(identity)

lines
  .map {
    case numPattern(a, b, c, d) =>
      val (xL, xU, yL, yU) = (a.toInt, b.toInt, c.toInt, d.toInt)
      (xL <= yL && yU <= xU) || (yL <= xL && xU <= yU)
    case l => throw new IllegalArgumentException(s"unable to parse: $l")
  }
  .count(identity)

// Part 2
lines
  .map {
    case numPattern(a, b, c, d) =>
      val (xL, xU, yL, yU) = (a.toInt, b.toInt, c.toInt, d.toInt)
      (xL to xU).intersect((yL to yU)).nonEmpty

    case l => throw new IllegalArgumentException(s"unable to parse: $l")
  }
  .count(identity)

lines
  .map {
    case numPattern(a, b, c, d) =>
      val (xL, xU, yL, yU) = (a.toInt, b.toInt, c.toInt, d.toInt)
      !(xU < yL || yU < xL)
    case l => throw new IllegalArgumentException(s"unable to parse: $l")
  }
  .count(identity)
