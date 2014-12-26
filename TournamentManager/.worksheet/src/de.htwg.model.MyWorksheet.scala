package de.htwg.model

object MyWorksheet {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(74); 
	val t1 = Team(1,"test",0,0,0);System.out.println("""t1  : de.htwg.model.Team = """ + $show(t1 ));$skip(30); 
	val t2 = Team(2,"FCB",0,0,0);System.out.println("""t2  : de.htwg.model.Team = """ + $show(t2 ));$skip(32); 
	val g1 = Game(1,(t1,t2),(0,0));System.out.println("""g1  : de.htwg.model.Game = """ + $show(g1 ));$skip(22); val res$0 = 
  g1.setResult((1,1));System.out.println("""res0: List[de.htwg.model.Team] = """ + $show(res$0));$skip(22); val res$1 = 
  g1.setResult((0,0));System.out.println("""res1: List[de.htwg.model.Team] = """ + $show(res$1));$skip(22); val res$2 = 
  g1.setResult((3,1));System.out.println("""res2: List[de.htwg.model.Team] = """ + $show(res$2))}
}
