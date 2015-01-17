package de.htwg.GUI

import scala.swing._
import scala.swing.event.ButtonClicked
import de.htwg.controller._
import java.awt.GridLayout
import scala.collection.mutable.ListBuffer
import java.awt.Dimension
import java.awt.Color
import java.awt.{Component => _}
import akka.actor.Actor
import akka.actor.Props
import akka.event.Logging
import akka.actor.ActorSystem
import akka.actor.ActorContext
import akka.actor.ActorRef

class GUI(controller: Controller) extends Frame {
  import controller.getEasyTable
  
  //def receive = {
    //case a: NewState => updateTables
    //case GroupWinnerFound(a,b) => new GroupWinner(a,b)
  //}
  
  //listenTo(controller)
  //reactions += {
	//case a: NewState => updateTables
  //case GroupWinnerFound(a,b) => new GroupWinner(a,b)
	//case _ => 
  //}
  
  
  val initTnG = new InitTnGDialog().init.getOrElse(throw new IllegalStateException("WTF"))
  val initGroups = new InitGroupsDialog(initTnG.cog).init.getOrElse(throw new IllegalStateException("WTF"))
  val initTeams = new InitTeamsDialog(initTnG.cot).init.getOrElse(throw new IllegalStateException("WTF"))
  initTeams.teams.foreach(controller.initTeams(_))
  initGroups.groups.reverse.foreach(controller.initGroups(initTnG.cog, _))
  
  val listOfButtons: ListBuffer[Button] = ListBuffer[Button]()
  val listOfGoals: ListBuffer[TextField] = ListBuffer[TextField]()
  val tables = List.range(0,initTnG.cog).map(x => new Table(controller.groups(0).teams.size+1, 4))
  updateTables
  
  val myMenuBar = new MenuBar {
    contents += new Menu("File") {
      contents += new MenuItem(Action("Players") { new PlayerManager(controller) })
      contents += new MenuItem(Action("Back Season") {
    	  listOfButtons.foreach(_.enabled = true) 
    	  listOfGoals.foreach(_.text = "0")
        })
      contents += new MenuItem(Action("Quit") { System.exit(0) })
    }
  }
  
  val boxPanels = for(i <- List.range(0,initTnG.cog)) yield
  	new BoxPanel(Orientation.Vertical) {
	  getGroupComponents(i).map(x => 
	    contents += x
	  )
	}
  
  val cols = initTnG.cog/2.toInt+1
  def myPanel: GridPanel = 
    if (initTnG.cog != 1) {
    	new GridPanel(2,cols) {
    		for(i <- 0 until initTnG.cog) {
          val bp = boxPanels(i)
          bp.border_=(javax.swing.BorderFactory.createLineBorder(Color.BLACK))
    			contents += bp
    		}
    	}
    }
  	else {
  		new GridPanel(1,cols) {
  			for(i <- 0 until initTnG.cog) {
  				val bp = boxPanels(i)
          bp.border
          contents += bp
  			}
  		}
  	}
  
  menuBar = myMenuBar
  contents = myPanel
  visible = true
	
	def updateTables = {
    var i = 0
    var j = 1
    for(table <- tables) {
    	table.update(0, 0, "Name")
    	table.update(0, 1, "Points")
    	table.update(0, 2, "Goals")
    	table.update(0, 3, "Goals")
      	for(team <- controller.groups(i).getEasyTable.reverse) {
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
    List(new FlowPanel { contents += new Label("Group " + controller.groups(i).name) }) :::
    controller.getGames(controller.groups(i)).map(x =>
      		new FlowPanel {
	  		  this.vGap = 0
	  		  val t1 = new Label(x.game._1.name)
	  		  t1.minimumSize = new Dimension(100,12)
	  		  t1.preferredSize = new Dimension(100,12)
	  		  t1.maximumSize = new Dimension(100,12)
	  		  contents += t1
	  		  contents += new Label(" - ")
	  		  val t2 = new Label(x.game._2.name)
	  		  t2.minimumSize = new Dimension(100,12)
	  		  t2.preferredSize = new Dimension(100,12)
	  		  t2.maximumSize = new Dimension(100,12)
	  		  contents += t2
	  		  contents += new Label("   ")
	  		  val goals = new TextField("0", 3)
	  		  contents += goals
	  		  contents += new Label(" : ")
	  		  val goalsAgainst = new TextField("0", 3)
	  		  contents += goalsAgainst
	  		  val commit = new Button("Commit")
	  		  commit.maximumSize = new Dimension(78,20)
	  		  commit.minimumSize = new Dimension(78,20)
	  		  commit.preferredSize = new Dimension(78,20)
	  		  listenTo(commit)
	  		  reactions += {
	  		    case ButtonClicked(button) =>
	  		    	controller.setGameResult(i+1, x.index, goals.text.toInt, goalsAgainst.text.toInt)
	  		    	commit.enabled = false
	  		  }
	  		  contents += commit
	  		  listOfButtons += commit
	  		  listOfGoals += goals += goalsAgainst
	  		}
	  	).toList :::
	  	List(tables(i))	  	
  }
}