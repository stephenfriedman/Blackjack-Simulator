

/**
 * @author stephen
 */
class IdealStrategy extends Strategy {
  //(row)(col)
  //player hand are the rows
  //dealer card are the columns
  val idealHard = Array.ofDim[Integer](17, 10)
  val idealSoft = Array.ofDim[Integer](9, 10)
  val idealPairs = Array.ofDim[Integer](10, 10)
  var chartNum = 0
  def getChartNum: Integer = { chartNum }
  //1 = hit
  //2 = stand
  //3 = split
  //4 = double down

  idealHard(0) = Array(1, 1, 1, 1, 1, 1, 1, 1, 1, 1)
  idealHard(1) = Array(1, 1, 1, 1, 1, 1, 1, 1, 1, 1)
  idealHard(2) = Array(1, 1, 1, 1, 1, 1, 1, 1, 1, 1)
  idealHard(3) = Array(1, 1, 1, 1, 1, 1, 1, 1, 1, 1)
  idealHard(4) = Array(1, 4, 4, 4, 4, 1, 1, 1, 1, 1)
  idealHard(5) = Array(4, 4, 4, 4, 4, 4, 4, 4, 1, 1)
  idealHard(6) = Array(4, 4, 4, 4, 4, 4, 4, 4, 4, 4)
  idealHard(7) = Array(1, 1, 2, 2, 2, 1, 1, 1, 1, 1)
  idealHard(8) = Array(2, 2, 2, 2, 2, 1, 1, 1, 1, 1)
  idealHard(9) = Array(2, 2, 2, 2, 2, 1, 1, 1, 1, 1)
  idealHard(10) = Array(2, 2, 2, 2, 2, 1, 1, 1, 1, 1)
  idealHard(11) = Array(2, 2, 2, 2, 2, 1, 1, 1, 1, 1)
  idealHard(12) = Array(2, 2, 2, 2, 2, 2, 2, 2, 2, 2)
  idealHard(13) = Array(2, 2, 2, 2, 2, 2, 2, 2, 2, 2)
  idealHard(14) = Array(2, 2, 2, 2, 2, 2, 2, 2, 2, 2)
  idealHard(15) = Array(2, 2, 2, 2, 2, 2, 2, 2, 2, 2)
  idealHard(16) = Array(2, 2, 2, 2, 2, 2, 2, 2, 2, 2)

  idealSoft(0) = Array(1, 1, 1, 4, 4, 1, 1, 1, 1, 1)
  idealSoft(1) = Array(1, 1, 1, 4, 4, 1, 1, 1, 1, 1)
  idealSoft(2) = Array(1, 1, 4, 4, 4, 1, 1, 1, 1, 1)
  idealSoft(3) = Array(1, 1, 4, 4, 4, 1, 1, 1, 1, 1)
  idealSoft(4) = Array(1, 4, 4, 4, 4, 1, 1, 1, 1, 1)
  idealSoft(5) = Array(4, 4, 4, 4, 4, 2, 2, 1, 1, 1)
  idealSoft(6) = Array(2, 2, 2, 2, 4, 2, 2, 2, 2, 2)
  idealSoft(7) = Array(2, 2, 2, 2, 2, 2, 2, 2, 2, 2)
  idealSoft(8) = Array(2, 2, 2, 2, 2, 2, 2, 2, 2, 2)

  idealPairs(0) = Array(3, 3, 3, 3, 3, 3, 1, 1, 1, 1)
  idealPairs(1) = Array(3, 3, 3, 3, 3, 3, 1, 1, 1, 1)
  idealPairs(2) = Array(1, 1, 1, 3, 3, 3, 1, 1, 1, 1)
  idealPairs(3) = Array(4, 4, 4, 4, 4, 4, 4, 4, 1, 1)
  idealPairs(4) = Array(3, 3, 3, 3, 3, 1, 1, 1, 1, 1)
  idealPairs(5) = Array(3, 3, 3, 3, 3, 3, 1, 1, 1, 1)
  idealPairs(6) = Array(3, 3, 3, 3, 3, 3, 3, 3, 3, 3)
  idealPairs(7) = Array(3, 3, 3, 3, 3, 2, 1, 1, 2, 2)
  idealPairs(8) = Array(2, 2, 2, 2, 2, 2, 2, 2, 2, 2)
  idealPairs(9) = Array(3, 3, 3, 3, 3, 3, 3, 3, 3, 3)

  var chart = Array.ofDim[Integer](17, 10)

  def handType(playerHand: Hand): Integer = {
    if (playerHand.isSoft == true) {
      chart = idealSoft
      chartNum = 2
    } else{
      chart = idealHard
      chartNum = 1
    }
    if(playerHand.card1.value == playerHand.cards(1).value) {
      chart = idealPairs
      chartNum = 3
    }
    chartNum

  }
  override def playStrategy(dealerCard: Card, playerHand: Hand, player: Player, shoe: Shoe, spot: Integer) {
    while (player.standing == false) {
      handType(player.hands(spot)._1)
      var decision = getDecision(getChartNum, dealerCard.value, player.hands(spot)._1)
      if (decision == 1)
        player.hit(shoe, player.hands(spot)._1)
      else if (decision == 2) {
        player.stick
      } else if (decision == 3) {
        player.split(shoe, player.hands(spot)._1)
      } else if (decision == 4) {
        player.doubleDown(shoe, player.hands(spot)._1)
      }
    }
  }
  def getDecision(chartNum: Integer, dealerCardValue: Integer, playerHand: Hand): Integer = {
   
    if (chartNum == 1) {
      chart(playerHand.getTotal - 5)(dealerCardValue - 2)
    } else if (chartNum == 2) {
      chart(playerHand.getTotal - 13)(dealerCardValue - 2)
    } else if (chartNum == 3) {
      chart(playerHand.card1.value - 2)(dealerCardValue - 2)
    } else 0
  }
}