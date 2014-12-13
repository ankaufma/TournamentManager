package de.htwg.GUI

import scala.swing._
import scala.swing.event.ButtonClicked
import de.htwg.controller._
import de.htwg.controller.Controller.getEasyTable	
import java.awt.GridLayout

object GUI extends SimpleSwingApplication {

  val initTnG = new InitTnGDialog().init.getOrElse(throw new IllegalStateException("WTF"))
  val initGroups = new InitGroupsDialog(initTnG.cog).init.getOrElse(throw new IllegalStateException("WTF"))
  val initTeams = new InitTeamsDialog(initTnG.cot).init.getOrElse(throw new IllegalStateException("WTF"))
  initTeams.teams.foreach(Controller.initTeams(_))
  initGroups.groups.reverse.foreach(Controller.initGroups(initTnG.cog, _))
  val tables = List.range(0,initTnG.cog).map(x => new Table(Controller.groups(0).teams.size+1, 4))
  updateTables
  
  val boxPanels = for(i <- List.range(0,initTnG.cog)) yield
  	new BoxPanel(Orientation.Vertical) {
	  getGroupComponents(i).map(x => 
	    contents += x
	  )
	}
  
  val cols = initTnG.cog/2.toInt+1
  val myPanel = new GridPanel(2,cols) {
	for(i <- 0 until initTnG.cog) {
		contents += boxPanels(i)
	}
  }
  myPanel.vGap = 10
  myPanel.hGap = 10
  def top = new MainFrame {
	title = "Hallo Welt"
	contents = myPanel
  }
	
	def updateTables = {
    var i = 0
    var j = 1
    for(table <- tables) {
    	table.update(0, 0, "Name")
    	table.update(0, 1, "Points")
    	table.update(0, 2, "Goals")
    	table.update(0, 3, "Goals")
      	for(team <- Controller.groups(i).getEasyTable.reverse) {
      		table.update(j, 0, team.name)
      		table.update(j, 1, team.points)
      		table.update(j, 2, team.goals)
      		table.update(j, 3, team.goalsAgainst)
      		j = j+1
      	}
      	j = 1
      	i = i+1
    }
  }
	
  def getGroupComponents(i: Int): List[Component] = {	  	
    Controller.getGames(Controller.groups(i)).map(x =>
	  		new FlowPanel {
	  		  contents += new Label(x.game._1.name)
	  		  contents += new Label(" - ")
	  		  contents += new Label(x.game._2.name)
	  		  contents += new Label("   ")
	  		  val goals = new TextField("0", 3)
	  		  contents += goals
	  		  contents += new Label(" : ")
	  		  val goalsAgainst = new TextField("0", 3)
	  		  contents += goalsAgainst
	  		  contents += Button("Commit") {
	  		    println(i+1 + ".groupindex " + x.index + ".gameindex " + goals.text.toInt + ".goals goalsagainst." + goalsAgainst.text.toInt)
	  		    Controller.setGameResult(i+1, x.index, goals.text.toInt, goalsAgainst.text.toInt)
	  		    updateTables
	  		  }
	  		}
	  	).toList :::
	  	List(tables(i))	  	
  }
}

case class InitTnG(cot: Int, cog: Int)
class InitTnGDialog extends Dialog {
  var init:Option[InitTnG] = None
  val cot = new TextField("0")
  val cog = new TextField("0")
    
  title = "Init Count of Teams and Groups"
  modal = true

  contents = new BorderPanel {
    layout(new BoxPanel(Orientation.Vertical) {
      border = Swing.EmptyBorder(5,5,5,5)
      contents += new Label("Count of Teams:")
      contents += cot
      contents += new Label("Count of Groups:")
      contents += cog
    }) = BorderPanel.Position.Center

    layout(new FlowPanel(FlowPanel.Alignment.Right)(
      Button("Submit") { 
    	  try {
    	  if(cot.text.toInt > 0 && cog.text.toInt > 0) {
    	    init = Some(InitTnG(cot.text.toInt, cog.text.toInt))
    	    endDialog
    	  } else {
    	    Dialog.showMessage(this, "Enter a number greater than zero", "Number Format Error", Dialog.Message.Error)
    	  }
    	  } catch {
    	    case nfe: NumberFormatException => 
    	      Dialog.showMessage(this, "Enter a number greater than zero", "Number Format Error", Dialog.Message.Error)
    	  }
      }
    )) = BorderPanel.Position.South
  }
  
  def endDialog() = this.close

  centerOnScreen
  open
}

case class InitGroups(groups: List[String])
class InitGroupsDialog(val cog: Int) extends Dialog {
  var init:Option[InitGroups] = None
  val listOfGroups: List[TextField] = for(i <- List.range(0, cog)) yield new TextField
  title = "Init Group Names"
  modal = true

  contents = new BorderPanel {
    layout(new BoxPanel(Orientation.Vertical) {
      border = Swing.EmptyBorder(5,5,5,5)
      for(i <- 0 until cog) {
        contents += new Label("Enter group name " + (i+1) + ":")
        contents += listOfGroups(i)
      }
    }) = BorderPanel.Position.Center

    layout(new FlowPanel(FlowPanel.Alignment.Right)(
      Button("Submit") { 
    	  init = Some(InitGroups(listOfGroups.map(x => x.text)))
    	  endDialog()
      }
    )) = BorderPanel.Position.South
  }
  
  def endDialog() = this.close

  centerOnScreen
  open
}

case class InitTeams(teams: List[String])
class InitTeamsDialog(val cot: Int) extends Dialog {
  var init:Option[InitTeams] = None
  val listOfTeams: List[TextField] = for(i <- List.range(0, cot)) yield new TextField(8)
  title = "Init Team Names"
  modal = true

  contents = new BorderPanel {
    if(cot<16) {
    layout(new BoxPanel(Orientation.Vertical) {
      border = Swing.EmptyBorder(5,5,5,5)
      for(i <- 0 until cot) {
        contents += new Label("Enter Team name " + (i+1) + ":")
        contents += listOfTeams(i)
      }
    }) = BorderPanel.Position.Center

    layout(new FlowPanel(FlowPanel.Alignment.Right)(
      Button("Submit") { 
    	  init = Some(InitTeams(listOfTeams.map(x => x.text)))
    	  endDialog()
      }
    )) = BorderPanel.Position.South
  } else {
        layout(new FlowPanel {
      border = Swing.EmptyBorder(5,5,5,5)
      for(i <- 0 until cot) {
        contents += new Label("Enter Team name " + (i+1) + ":")
        contents += listOfTeams(i)
      }
    }) = BorderPanel.Position.Center

    layout(new FlowPanel(FlowPanel.Alignment.Right)(
      Button("Submit") { 
    	  init = Some(InitTeams(listOfTeams.map(x => x.text)))
    	  endDialog()
      }
    )) = BorderPanel.Position.South
  }}
  
  def endDialog() = this.close

  centerOnScreen
  open
}