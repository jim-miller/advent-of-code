import scala.io.Source
import scala.util.Using

val ex1 = "bvwbjplbgvbhsrlpgdmjqwftvncz"
val ex2 = "nppdvjthqldpwncqszvftbrmjlhg"
val ex3 = "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg"
val ex4 = "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"

val input = Using(Source.fromResource("day06/input.txt"))(_.getLines().mkString).get

val firstUniq = (s: String, gSize: Int) => s.sliding(gSize).takeWhile(_.toSet.size != gSize).length + gSize
val startOfSignal = firstUniq(_, 4)
val startOfMessage = firstUniq(_, 14)


assert(startOfSignal(ex1) == 5)
assert(startOfSignal(ex2) == 6)
assert(startOfSignal(ex3) == 10)
assert(startOfSignal(ex4) == 11)

// part 1
startOfSignal(input)

// part 2
assert(startOfMessage("mjqjpqmgbljsphdztnvjfqwrcgsmlb") == 19)
assert(startOfMessage("bvwbjplbgvbhsrlpgdmjqwftvncz") == 23)
assert(startOfMessage("nppdvjthqldpwncqszvftbrmjlhg") == 23)
assert(startOfMessage("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg") == 29)
assert(startOfMessage("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw") == 26)

startOfMessage(input)
