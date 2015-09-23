

/**
 * @author stephen
 */
class Player(var strategy: Strategy, var money: Double) {

  var hands = new Array[(Hand, Double)](30)
  var stand = false
  var wins=0
  var losses=0
  var blackjacks=0
  var pushes=0
  var splits=0
  def standing: Boolean = {
    stand
  }
  var numHands = 0
  var currentPlayingHandNumInArray = 0
  def getCurrentHandNum: Integer = { currentPlayingHandNumInArray }
  def playStrategy(dealerCard: Card, shoe: Shoe) {
    var finished = false
    while (!finished) {
      stand = false
      strategy.playStrategy(dealerCard, hands(currentPlayingHandNumInArray)._1, this, shoe, currentPlayingHandNumInArray)
      currentPlayingHandNumInArray += 1
      if (numHands == currentPlayingHandNumInArray)
        finished = true
    }

  }
  def dealHand(dealtHand: Hand, bet: Double) {
    hands(currentPlayingHandNumInArray) = (dealtHand, bet)
    numHands += 1
  }

  def hit(shoe: Shoe, playingHand: Hand) {
    playingHand.addCard(shoe.getCard)
    if (playingHand.getTotal >= 21)
      stand = true
  }

  def stick =
    {
      stand = true
    }

  def split(shoe: Shoe, playingHand: Hand) {
    splits+=1
    var splitCard = playingHand.cards(1)
    var newCard = shoe.getCard
    hands(currentPlayingHandNumInArray)._1.newCard2(newCard)
    numHands += 1
    var newCard2 = shoe.getCard
    var hand2 = new Hand(splitCard, newCard2)
    hands(numHands - 1) = (hand2, hands(currentPlayingHandNumInArray)._2)
  }

  def doubleDown(shoe: Shoe, playingHand: Hand) {
    var currentBet = hands(numHands - 1)._2
    hands(numHands - 1) = (playingHand, currentBet * 2)
    hit(shoe, hands(numHands - 1)._1)
    stand = true
  }

  def loseHand(spot: Integer): Double =
    {
      var loss = hands(spot)._2
      money -= loss
      losses+=1
      loss
    }

  def twoCardBlackJack(spot: Integer): Double =
    {
      var win = hands(spot)._2 * 1.5
      money += win
      wins+=1
      blackjacks+=1
      win
    }

  def push={
    pushes+=1
  }
  def winHand(spot: Integer): Double =
    {
      var win = hands(spot)._2
      money += win
      wins+=1
      win
    }
  def clearHands() {
    hands = new Array[(Hand, Double)](30)
    currentPlayingHandNumInArray = 0
    numHands = 0
    stand = false
  }
}