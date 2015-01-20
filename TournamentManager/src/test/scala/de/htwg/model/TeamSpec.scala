package testTeam

import de.htwg.model.Team

import org.specs2.mutable._

/**
 * Created by ankaufma on 20.01.2015.
 */
class TeamSpec extends Specification {
  val team = Team(0,"FCB",0,0,0)

  "A new Team" should {
    "have been createtd with name 'FCB'" in {
      team.name must be_==("FCB")
    }
  }

  "A new Team" should {
    "have been createtd with name index '0'" in {
      team.index must be_==(0)
    }
  }

  "A new Team" should {
    "have been createtd with name points '0'" in {
      team.points must be_==(0)
    }
  }

  "A new Team" should {
    "have been createtd with name goals '0'" in {
      team.goals must be_==(0)
    }
  }

  "A new Team" should {
    "have been createtd with name goals Against '0'" in {
      team.goalsAgainst must be_==(0)
    }
  }
}
