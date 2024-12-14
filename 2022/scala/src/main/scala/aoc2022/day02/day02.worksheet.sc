import scala.util.Using
import scala.io.Source

sealed abstract class Play(val value: Int) extends Product with Serializable
case object Rock                           extends Play(1)
case object Paper                          extends Play(2)
case object Scissors                       extends Play(3)

sealed trait Cmd extends Product with Serializable
case object Lose extends Cmd
case object Draw extends Cmd
case object Win  extends Cmd

val TieBonus = 3
val WinBonus = 6

val availablePlays              = Array(Rock, Paper, Scissors)
def winsAgainst(p: Play): Play  = availablePlays(p.value % 3)
def losesAgainst(p: Play): Play = winsAgainst(winsAgainst(p))

implicit val playKey = Map("A" -> Rock, "B" -> Paper, "C" -> Scissors, "X" -> Rock, "Y" -> Paper, "Z" -> Scissors)
implicit val cmdKey  = Map("X" -> Lose, "Y" -> Draw, "Z" -> Win)

def parseFor[A](s: String)(implicit key: Map[String, A]): (Play, A) = s.split(" ") match {
  case Array(opp, me) => (playKey(opp), key(me))
  case _              => throw new IllegalArgumentException(s"Unable to parse game input: $s")
}

def scoreGame(opp: Play, me: Play): Int = (opp, me) match {
  case (o, m) if o == winsAgainst(m) => m.value
  case (o, m) if o == m              => m.value + TieBonus
  case (_, m)                        => m.value + WinBonus
}

def scoreCmd(opp: Play, me: Cmd): Int = (opp, me) match {
  case (p, Lose) => scoreGame(p, losesAgainst(p))
  case (p, Draw) => scoreGame(p, p)
  case (p, Win)  => scoreGame(p, winsAgainst(p))
}

Using(Source.fromResource("day02/input.txt")) { src =>
  val plays = src.getLines().toList

  val part1 = plays.map(parseFor[Play]).map((scoreGame _).tupled).sum
  val part2 = plays.map(parseFor[Cmd]).map((scoreCmd _).tupled).sum

  (part1, part2)
}.get
