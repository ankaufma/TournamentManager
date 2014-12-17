package de.htwg.GUI

import scala.swing._

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