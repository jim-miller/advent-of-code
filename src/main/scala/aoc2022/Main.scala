package aoc2022

import cats.effect.{IO, IOApp}
import cats.syntax.show._
import aoc2022.config._
import aoc2022.resource._
import aoc2022.Day01

object Main extends IOApp.Simple {

  override def run =
    Config
      .load[IO]
      .flatMap { cfg =>
        val res = AppResources
          .make[IO](cfg)
        val d1IO = Day01.make[IO](res.day1)

        val answers = for {
          d1    <- d1IO
          d1Ans <- d1.part1
          d2Ans <- d1.part2
        } yield List(d1Ans, d2Ans)

        answers.map(_.foreach(a => println(a.show)))
      }
}
