

/**
 * Stephen A. Friedman
 * 9/22/2015
 *
 * This program factors in the basic rules of blackjack including the following:
 * 6 decks, no insurance, splitting on aces, re-splitting, dealer stands on 17 no exceptions
 *
 * Currently plays 3 players and a dealer.
 * 2 players are using the IdealStrategy and 1 player uses a dealer strategy
 *
 * Default bet setting is always $1, $2 when doubling down
 *
 * Default set to run through 10,000 games
 * 
 * Average house edge is calculated by:
 * sum of the house edges against each player divided by number of players
 * house edge = 100*(number of losses - number of wins)/(number of losses + number of wins)
 */
object Runner {
  def main(args: Array[String]) {
    var shoe = new Shoe(6)
    var p1 = new Player(new IdealStrategy, 100000)
    var p2 = new Player(new IdealStrategy, 100000)
    var p3 = new Player(new DealerStrategy,100000)
    var players = new Array[Player](3)
    players(0) = p1
    players(1) = p2
    players(2) = p3
    var d = new Dealer(shoe, players, 10)
    for (i <- 1 to 1000) {
      shoe = new Shoe(6)
      shoe.shuffle
      d.newShoe(shoe)
      d.playGames
    }
    println("Dealer's money: $" + d.money)
    println("Player 1's money: $" + p1.money + "       wins: " + p1.wins + "      losses: " + p1.losses + "      blackjacks: " + p1.blackjacks + "     pushes:" + p1.pushes+ "     splits:" + p1.splits)
    println("Player 2's money: $" + p2.money + "       wins: " + p2.wins + "      losses: " + p2.losses + "      blackjacks: " + p2.blackjacks + "     pushes:" + p2.pushes+ "     splits:" + p2.splits)
    println("Player 3's money: $" + p3.money + "       wins: " + p3.wins + "      losses: " + p3.losses + "      blackjacks: " + p3.blackjacks + "     pushes:" + p3.pushes+ "     splits:" + p3.splits)

    var houseEdge=0.0000
    for (player <- players) {
      var edgeAgainstPlayer = 100*(player.losses-player.wins).toDouble/(player.losses+player.wins+player.splits).toDouble
      houseEdge+=edgeAgainstPlayer
    }
    houseEdge=houseEdge/players.length.toDouble
    println("Average house edge over the "+players.length+" players is: "+houseEdge+"%")
  }

}