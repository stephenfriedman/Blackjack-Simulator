

/**
 * @author stephen
 */
class Dealer(givenShoe: Shoe, players: Array[Player], games: Integer) {
  var shoe = givenShoe
  var money = 1000000.0
  def newShoe(nShoe: Shoe) {
    shoe = nShoe
  }
  def playGames {
    for (i <- 0 to games - 1) {
      var dealerHand = new Hand(shoe.getCard, shoe.getCard)
      for (player <- players) {
        player.dealHand(new Hand(shoe.getCard, shoe.getCard), 1)
      }
      for (player <- players) {
        player.playStrategy(dealerHand.card1, shoe)
      }

      while (dealerHand.getTotal < 17) {
        dealerHand.addCard(shoe.getCard)
      }
      if (dealerHand.getTotal > 21) {
        for (player <- players) {
          for (i <- 0 to player.numHands - 1) {
            if (player.hands(i)._1.getTotal <= 21) {
              if (player.hands(i)._1.getTotal == 21) {
                if (player.hands(i)._1.numCards == 2) {
                  money -= player.twoCardBlackJack(i)
                } else
                  money -= player.winHand(i)

              } else if (player.hands(i)._1.getTotal < 21) {
                money -= player.winHand(i)
              }
            } else
              money += player.loseHand(i)
          }
        }
      }
      else if (dealerHand.getTotal <= 21) {
        for (player <- players) {
          for (i <- 0 to player.numHands - 1) {
            //            if (player.hands(i)._1.getTotal == dealerHand.getTotal || (player.hands(i)._1.getTotal > 21 && dealerHand.getTotal > 21)) {
            //              if (player.hands(i)._1.numCards == 2 || dealerHand.numCards == 2) {
            //                //PUSH
            //              } else if (player.hands(i)._1.numCards == 2 || dealerHand.numCards > 2)
            //                player.twoCardBlackJack(i)
            //              else if (player.hands(i)._1.numCards > 2 || dealerHand.numCards == 2) {
            //                player.loseHand(i)
            //              }
            //            } else if (player.hands(i)._1.getTotal > dealerHand.getTotal && (player.hands(i)._1.getTotal <= 21)) {
            //              var houseLoss = player.winHand(i)
            //              money -= houseLoss
            //            } else if (player.hands(i)._1.getTotal > 21 && dealerHand.getTotal <= 21) {
            //              //player already paid up for busting
            //              var houseWin = player.loseHand(i)
            //              money += houseWin
            //            } else if (player.hands(i)._1.getTotal < dealerHand.getTotal) {
            //              var houseWin = player.loseHand(i)
            //              money += houseWin
            //            }
            if (player.hands(i)._1.getTotal > 21)
              money += player.loseHand(i)
            else if (player.hands(i)._1.getTotal == 21) {
              if (dealerHand.getTotal == 21) {
                player.push
              } else {
                if (player.hands(i)._1.numCards == 2) {
                  money -= player.twoCardBlackJack(i)
                } else {
                  money -= player.winHand(i)
                }
              }
            }
            else
              if(player.hands(i)._1.getTotal>dealerHand.getTotal)
              {
                money-=player.winHand(i)
              }
              else if(player.hands(i)._1.getTotal<dealerHand.getTotal)
              {
                money+=player.loseHand(i)
              }
              else
              {
                player.push
              }
          }
        }
      }
      for (player <- players) {
        player.clearHands()
      }
    }
  }
}