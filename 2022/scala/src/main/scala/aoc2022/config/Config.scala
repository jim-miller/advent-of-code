package aoc2022.config

import aoc2022.Day
import cats.Applicative
import cats.syntax.applicative._

object Config {
  def load[F[_]: Applicative]: F[AppConfig] =
    AppConfig(Map(Day(1) -> "day01/input.txt")).pure[F]
}
