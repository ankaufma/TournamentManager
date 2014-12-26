package de.htwg.GUI

import scala.swing._
import scala.swing.event.ButtonClicked
import de.htwg.controller._
import java.awt.GridLayout
import scala.collection.mutable.ListBuffer
import java.awt.Dimension

class GUI(controller: Controller) extends Frame {
  import controller.getEasyTable
  
  listenTo(controller)
  reactions += {
	case a: NewState => updateTables
	case _ => 
  }
  
  val initTnG = new InitTnGDialog().init.getOrElse(throw new IllegalStateException("WTF"))
  val initGroups = new InitGroupsDialog(initTnG.cog).init.getOrElse(throw new IllegalStateException("WTF"))
  val initTeams = new InitTeamsDialog(initTnG.cot).init.getOrElse(throw new IllegalStateException("WTF"))
  val listOfButtons: ListBuffer[Button] = ListBuffer[Button]()
  val listOfGoals: ListBuffer[TextField] = ListBuffer[TextField]()
  
  initTeams.teams.foreach(controller.initTeams(_))
  initGroups.groups.reverse.foreach(controller.initGroups(initTnG.cog, _))
  val tables = List.range(0,initTnG.cog).map(x => new Table(controller.groups(0).teams.size+1, 4))
  updateTables
  
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
    			contents += boxPanels(i)
    		}
    	}
    }
  	else {
  		new GridPanel(1,cols) {
  			for(i <- 0 until initTnG.cog) {
  				contents += boxPanels(i)
  			}
  		}
  	}
  
  val myMenuBar = new MenuBar {
    contents += new Menu("File") {
      contents += new MenuItem(Action("Back Season") {
    	  listOfButtons.foreach(_.enabled = true) 
    	  listOfGoals.foreach(_.text = "0")
        })
      contents += new MenuItem(Action("Quit") { System.exit(0) })
    }
  }
  
  contents = myPanel
  visible = true
  menuBar = myMenuBar
	
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
    controller.getGames(controller.groups(i)).map(x =>
	  		new FlowPanel {
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