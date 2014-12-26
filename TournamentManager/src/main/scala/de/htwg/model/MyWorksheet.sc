package de.htwg.model

object MyWorksheet {
	val t1 = Team(1,"test",0,0,0)             //> t1  : de.htwg.model.Team = Team(1,test,0,0,0)
	val t2 = Team(2,"FCB",0,0,0)              //> t2  : de.htwg.model.Team = Team(2,FCB,0,0,0)
	val g1 = Game(1,(t1,t2),(0,0))            //> g1  : de.htwg.model.Game = Game(1,(Team(1,test,0,0,0),Team(2,FCB,0,0,0)),(0,
                                                  //| 0))
  g1.setResult((1,1))                             //> remis
                                                  //| res0: List[de.htwg.model.Team] = List(Team(1,test,1,1,1), Team(2,FCB,1,1,1))
                                                  //| 
  g1.setResult((0,0))                             //> remis
                                                  //| res1: List[de.htwg.model.Team] = List(Team(1,test,1,0,0), Team(2,FCB,1,0,0))
                                                  //| 
  g1.setResult((3,1))                             //> home won
                                                  //| res2: List[de.htwg.model.Team] = List(Team(1,test,3,3,1), Team(2,FCB,0,1,3))
                                                  //| 
}