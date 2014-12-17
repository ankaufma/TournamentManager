package de.htwg.GUI
import scala.swing._

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