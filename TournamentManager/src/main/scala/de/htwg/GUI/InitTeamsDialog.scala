package de.htwg.GUI
import scala.swing._

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