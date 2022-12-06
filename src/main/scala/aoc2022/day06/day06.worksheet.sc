import scala.io.Source
import scala.util.Using

val ex1 = "bvwbjplbgvbhsrlpgdmjqwftvncz"
val ex2 = "nppdvjthqldpwncqszvftbrmjlhg"
val ex3 = "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg"
val ex4 = "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"

val input = Using(Source.fromResource("day06/input.txt"))(_.mkString).get

def firstUniq(s: String, window: Int): Int =
  s.sliding(window)
    .takeWhile(_.toSet.size != window)
    .length + window

// part 1
def startOfSignal = firstUniq(_, 4)

assert(startOfSignal(ex1) == 5)
assert(startOfSignal(ex2) == 6)
assert(startOfSignal(ex3) == 10)
assert(startOfSignal(ex4) == 11)

startOfSignal(input)

// part 2
def startOfMessage = firstUniq(_, 14)

assert(startOfMessage("mjqjpqmgbljsphdztnvjfqwrcgsmlb") == 19)
assert(startOfMessage("bvwbjplbgvbhsrlpgdmjqwftvncz") == 23)
assert(startOfMessage("nppdvjthqldpwncqszvftbrmjlhg") == 23)
assert(startOfMessage("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg") == 29)
assert(startOfMessage("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw") == 26)

startOfMessage(input)
