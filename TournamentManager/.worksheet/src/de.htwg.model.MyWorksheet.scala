package de.htwg.model

object MyWorksheet {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(130); val res$0 = 
	"1. 24 3:1".replaceAll(":", " ").replaceAll("\\.", "").split(" ").toList.map(_.toInt);System.out.println("""res0: List[Int] = """ + $show(res$0))}
  
  
}
