package aoc2022

import cats.Show

final case class Answer[A](q: String, a: A)

object Answer {
  implicit def AnswerShowInt[A: Numeric]: Show[Answer[A]] = Show.show { a =>
    val formatter = java.text.NumberFormat.getNumberInstance

    val formattedQuestion = if (a.q.endsWith("?")) a.q else a.q + "?"
    val formattedAnswer   = formatter.format(Numeric[A].toInt(a.a).toLong)

    s"$formattedQuestion: $formattedAnswer"
  }
}
