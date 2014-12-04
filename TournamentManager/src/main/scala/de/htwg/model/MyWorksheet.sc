package de.htwg.model

object MyWorksheet {
	"1. 24 3:1".replaceAll(":", " ").replaceAll("\\.", "").split(" ").toList.map(_.toInt)
                                                  //> res0: List[Int] = List(1, 24, 3, 1)
  
  
}