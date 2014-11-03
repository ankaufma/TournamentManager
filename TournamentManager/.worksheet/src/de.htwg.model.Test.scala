package de.htwg.model

object Test {
case class Team(index: Int, name: String, points: Int, goals: Int, goalsAgainst: Int) {
	def fin = this.finalize
};import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(207); 

val l1 = List(Team(1,"FCK",0,0,0),Team(2,"FCB",0,0,0));System.out.println("""l1  : List[de.htwg.model.Test.Team] = """ + $show(l1 ));$skip(34); 
val l2 = l1.map(_.copy(points=3));System.out.println("""l2  : List[de.htwg.model.Test.Team] = """ + $show(l2 ));$skip(24); val res$0 = 
l2.map(_.name.toString);System.out.println("""res0: List[String] = """ + $show(res$0));$skip(26); val res$1 = 
l2.map(_.points.toString);System.out.println("""res1: List[String] = """ + $show(res$1))}
}
