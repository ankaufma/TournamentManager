package de.htwg.model

object MyWorksheet {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(76); 
  val t1 = Team(1, "FCB",0,0,0);System.out.println("""t1  : de.htwg.model.Team = """ + $show(t1 ));$skip(33); ;
  val t2 = Team(2, "FCK",0,0,0);System.out.println("""t2  : de.htwg.model.Team = """ + $show(t2 ));$skip(33); ;
  val t3 = Team(3, "KSC",0,0,0);System.out.println("""t3  : de.htwg.model.Team = """ + $show(t3 ));$skip(33); ;
  val t4 = Team(4, "BVB",0,0,0);System.out.println("""t4  : de.htwg.model.Team = """ + $show(t4 ));$skip(48); ;
  
  val g1 = new Group("A", List(t1,t2,t3,t4));System.out.println("""g1  : de.htwg.model.Group = """ + $show(g1 ));$skip(17); 
  g1.calcMatches;$skip(82); val res$0 = 
  
  //Oben gg. Unten in dieser Reihenfolge
  g1.getGames.map(_.getMatch._1.name);System.out.println("""res0: List[String] = """ + $show(res$0));$skip(38); val res$1 = 
  g1.getGames.map(_.getMatch._2.name);System.out.println("""res1: List[String] = """ + $show(res$1));$skip(14); 
  g1.getTable;$skip(23); 
	val gl1 = g1.getGames;System.out.println("""gl1  : List[de.htwg.model.Game] = """ + $show(gl1 ));$skip(107); 
  //FCB gg. FCK
val g2 = g1.copy(teams = g1.teams :::
  gl1(0).setResult(7,3) :::
 	gl1(1).setResult(4,1));System.out.println("""g2  : de.htwg.model.Group = """ + $show(g2 ));$skip(17); val res$2 = 
g2.teams.drop(4);System.out.println("""res2: List[de.htwg.model.Team] = """ + $show(res$2));$skip(18); 

  
  g2.getTable}
  
  
}
