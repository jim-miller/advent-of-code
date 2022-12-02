import Dependencies._

ThisBuild / scalaVersion := "2.13.8"
ThisBuild / semanticdbEnabled := true
ThisBuild / semanticdbVersion := scalafixSemanticdb.revision
ThisBuild / scalafixDependencies += "com.github.liancheng" %% "organize-imports" % "0.6.0"

lazy val root = project
  .in(file("."))
  .settings(
    libraryDependencies ++= Seq(catsEffect)
  )
