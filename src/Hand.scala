

/**
 * @author stephen
 */
class Hand(var card1: Card, var card2: Card) {
  var cards = new Array[Card](15)
  var numCards = 0
  cards(numCards) = card1
  numCards += 1
  cards(numCards) = card2
  numCards += 1
  var soft = false
  if (card1.value == 11 || card2.value == 11)
    soft = true

  def isSoft: Boolean = {
    var totalWithoutAces = 0
    var acesPresent = 0
    if (cards(0).value == 11 || cards(1).value == 11) {
      soft = true
    }
    for (i <- 0 to numCards - 1) {
      if (cards(i).value != 11)
        totalWithoutAces += cards(i).value
      else
        acesPresent += 1
    }
    if (acesPresent > 0 && totalWithoutAces + acesPresent - 1 <= 9)
      soft = true
    else
      soft = false
    if (cards(0).value + cards(1).value == 21)
      soft = false
    soft
  }
  def getTotal: Integer = {
    var total = 0
    var acesAs11=0
    for (i <- 0 to numCards - 1) {
      if (isSoft) {
        if (cards(i).value == 11 && cards(i).value + total > 21) {
          total += 1
          acesAs11+=1
        } else
        {
          if(total+cards(i).value>21 && acesAs11>0)
          {
            total-=10
            total+=cards(i).value
          }
          else
            total+=cards(i).value
        }
          
      } else {
        if (cards(i).value == 11)
          total += 1
        else
          total += cards(i).value
      }
      if (cards(0).value + cards(1).value == 21) {
        total = 21
      }
    }
    var checkFor21 = 0
    for (i <- 0 to numCards - 1) {
      checkFor21 += cards(i).value
    }
    if (checkFor21 == 21) {
      total = 21
    }
    total
  }
  def addCard(card: Card) =
    {
      cards(numCards) = card
      numCards += 1
    }
  def newCard2(card: Card) {
    cards(1) = card
  }

}