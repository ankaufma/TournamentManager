package de.htwg.model

case class Team(index: Int, name: String, points: Int, goals: Int, goalsAgainst: Int) {
	def fin = this.finalize
}