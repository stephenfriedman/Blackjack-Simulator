

/**
 * @author stephen
 */
class DealerStrategy extends Strategy{
  override def playStrategy(dealerCard: Card, playerHand: Hand, player: Player, shoe: Shoe, spot: Integer){
    while(playerHand.getTotal<17)
      player.hit(shoe, player.hands(spot)._1)
  }
}