

/**
 * @author stephen
 */
class Shoe(var numDecks: Integer) {
  var shoe = new Array[Card](numDecks * 52)
  var cardSpot = 0
  var counter = 0
  for (i <- 0 to numDecks - 1) {
    var d = new Deck(new Array[Card](52))
    for (j <- 0 to d.deck.length - 1) {
      shoe(counter) = d.deck(j)
      counter += 1
    }
  }

  def shuffle {
    for (i <- 0 to numDecks * 52 * 50) {
      var spotA = scala.util.Random.nextInt(shoe.length)
      var spotB = scala.util.Random.nextInt(shoe.length)
      while (spotB == spotA) {
        spotB = scala.util.Random.nextInt(shoe.length)
      }
      var spotACard = shoe(spotA)
      shoe(spotA) = shoe(spotB)
      shoe(spotB) = spotACard
    }
  }
   def getShoe: Array[Card]={
     shoe
   }
  def  getCard: Card = {
    cardSpot += 1
    shoe(cardSpot-1)
  }

}