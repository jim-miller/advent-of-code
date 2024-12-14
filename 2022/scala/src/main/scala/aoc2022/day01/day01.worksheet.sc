import scala.util.Using
import scala.io.Source

// Day 01
val elfPacks = Using(Source.fromResource("day01/input.txt")) { src =>
  src.mkString
    .split("\n\n")
    .map(
      _.split("\n")
        .map(_.toInt)
    )
}.get

val orderedSummed = elfPacks.map(_.sum).sorted

val part1 = orderedSummed.last
val part2 = orderedSummed.takeRight(3).sum

