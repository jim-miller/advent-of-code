package aoc2022

import scala.io.Source

import cats.effect.{MonadCancelThrow, Resource}
import cats.syntax.applicative._

trait Day01[F[_]] {
  def part1: F[Answer[Int]]
  def part2: F[Answer[Int]]
}

object Day01 {
  def make[F[_]: MonadCancelThrow](src: Resource[F, Source]): F[Day01[F]] = {
    src.use { src =>
      val elfTotals =
        src
          .getLines()
          .foldLeft(List.empty[List[Int]]) {
            case (h :: t, s) if !s.isEmpty => (s.toInt :: h) :: t
            case (Nil, s)                  => List(s.toInt) :: Nil
            case (l, _)                    => List.empty :: l
          }
          .filterNot(_.isEmpty)
          .map(_.sum).sorted

      new Day01[F] {
        override def part1: F[Answer[Int]] = {
          Answer(
            "How many total Calories is that Elf carrying?",
            elfTotals.last
          ).pure
        }
        override def part2: F[Answer[Int]] =
          Answer(
            "How many Calories are those Elves carrying in total",
            elfTotals.takeRight(3).sum
          ).pure
      }.pure
    }
  }
}
