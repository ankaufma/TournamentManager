package de.htwg.model

object MyWorksheet {
  val t1 = Team(1, "FCB",0,0,0);                  //> t1  : de.htwg.model.Team = Team(1,FCB,0,0,0)
  val t2 = Team(2, "FCK",0,0,0);                  //> t2  : de.htwg.model.Team = Team(2,FCK,0,0,0)
  val t3 = Team(3, "KSC",0,0,0);                  //> t3  : de.htwg.model.Team = Team(3,KSC,0,0,0)
  val t4 = Team(4, "BVB",0,0,0);                  //> t4  : de.htwg.model.Team = Team(4,BVB,0,0,0)
  
  val g1 = new Group("A", List(t1,t2,t3,t4))      //> g1  : de.htwg.model.Group = Group(A,List(Team(1,FCB,0,0,0), Team(2,FCK,0,0,0
                                                  //| ), Team(3,KSC,0,0,0), Team(4,BVB,0,0,0)))
  g1.calcMatches
  
  //Oben gg. Unten in dieser Reihenfolge
  g1.getGames.map(_.getMatch._1.name)             //> res0: List[String] = List(FCB, FCK, KSC, BVB, FCB, FCK)
  g1.getGames.map(_.getMatch._2.name)             //> res1: List[String] = List(FCK, KSC, BVB, FCB, KSC, BVB)
  g1.getTable                                     //> FCB | 0 | 0 | 0
                                                  //| FCK | 0 | 0 | 0
                                                  //| KSC | 0 | 0 | 0
                                                  //| BVB | 0 | 0 | 0
	val gl1 = g1.getGames                     //> gl1  : List[de.htwg.model.Game] = List(Game(1,(Team(1,FCB,0,0,0),Team(2,FCK,
                                                  //| 0,0,0))), Game(2,(Team(2,FCK,0,0,0),Team(3,KSC,0,0,0))), Game(3,(Team(3,KSC,
                                                  //| 0,0,0),Team(4,BVB,0,0,0))), Game(4,(Team(4,BVB,0,0,0),Team(1,FCB,0,0,0))), G
                                                  //| ame(5,(Team(1,FCB,0,0,0),Team(3,KSC,0,0,0))), Game(6,(Team(2,FCK,0,0,0),Team
                                                  //| (4,BVB,0,0,0))))
  //FCB gg. FCK
val g2 = g1.copy(teams = g1.teams :::
  gl1(0).setResult(7,3) :::
 	gl1(1).setResult(4,1))                    //> g2  : de.htwg.model.Group = Group(A,List(Team(1,FCB,0,0,0), Team(2,FCK,0,0,0
                                                  //| ), Team(3,KSC,0,0,0), Team(4,BVB,0,0,0), Team(1,FCB,3,7,7), Team(2,FCK,0,3,3
                                                  //| ), Team(2,FCK,3,4,4), Team(3,KSC,0,1,1)))
g2.teams.drop(4)                                  //> res2: List[de.htwg.model.Team] = List(Team(1,FCB,3,7,7), Team(2,FCK,0,3,3), 
                                                  //| Team(2,FCK,3,4,4), Team(3,KSC,0,1,1))

  
  g2.getTable                                     //> FCB | 3 | 7 | 7
                                                  //| FCK | 3 | 4 | 4
                                                  //| FCB | 0 | 0 | 0
                                                  //| FCK | 0 | 0 | 0
                                                  //| KSC | 0 | 0 | 0
                                                  //| BVB | 0 | 0 | 0
                                                  //| FCK | 0 | 3 | 3
                                                  //| KSC | 0 | 1 | 1
  
  
}