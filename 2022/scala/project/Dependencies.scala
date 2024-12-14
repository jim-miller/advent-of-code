import sbt._

object Dependencies {
  object V {
    val catsEffect = "3.4.2"
  }

  lazy val catsEffect = "org.typelevel" %% "cats-effect" % V.catsEffect
}
