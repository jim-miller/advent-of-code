package aoc2022.resource

import scala.io.Source

import aoc2022.Day
import aoc2022.config.AppConfig
import cats.effect.{Resource, Sync}
import cats.syntax.applicative._

sealed abstract class AppResources[F[_]](
    val day1: Resource[F, Source]
)

object AppResources {
  def make[F[_]: Sync](cfg: AppConfig): AppResources[F] = {
    new AppResources[F](
      Resource.fromAutoCloseable(
        Source.fromResource(cfg.data(Day(1))).pure
      )
    ) {}
  }
}
