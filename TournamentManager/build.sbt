name := "TournamentManager"

version := "2.10"

scalaVersion := "2.11.4"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
  "com.escalatesoft.subcut" %% "subcut" % "2.1",
  "com.typesafe.akka" %% "akka-actor" % "2.3.8",
  "org.scala-lang" % "scala-swing" % "2.11.0-M7",
  "org.specs2" %% "specs2-core" % "2.4.15" % "test"
)
