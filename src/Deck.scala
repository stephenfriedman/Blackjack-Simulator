

/**
 * @author stephen
 */
class Deck(var deck: Array[Card]) {
  var x = 4
  var y = 11
  var counter = 0;
  for (i <- 1 to x) {
    for (j <- 2 to y) {
      deck(counter) = new Card(j)
      counter += 1
    }
  }
  for (i <- counter to deck.length-1) {
    deck(counter) = new Card(10)
    counter+=1
  }

}