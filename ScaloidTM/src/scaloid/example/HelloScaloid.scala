package scaloid.example

import _root_.controllers.Controller
import android.graphics.Color
import android.text.Layout
import android.view.View
import org.scaloid.common._
import android.text.InputType._
import scaloid.example.controllers.DefaultInit

import scala.collection.mutable.ListBuffer

class MainView extends SActivity {
  DefaultInit
  val tables: ListBuffer[STableLayout] = ListBuffer()
  onCreate {
    contentView = new SVerticalLayout {
      style {
        case t: STextView =>
          t textColor (Color.WHITE)
          t width (50)
        case e: SEditText => e textColor (Color.BLACK)
        case b: SButton => b textColor (Color.BLACK)
      }
      this += new SScrollView {
      this += new SVerticalLayout {
        for(i <- 0 until Controller.groups.size) {
          for(j <- 0 until Controller.groups(i).games.size) {
            this += new SLinearLayout {
              STextView(Controller.groups(i).games(j).getMatch._1.name)
              STextView(" ").width(3)
              STextView("-").width(3)
              STextView(" ").width(3)
              STextView(Controller.groups(i).games(j).getMatch._2.name)
              STextView("").width(20)
              val r1 = SEditText() inputType TYPE_CLASS_NUMBER
              r1 setText (Controller.groups(i).games(j).getResult._1.toString)
              r1 width (40)
              STextView(":").width(4)
              val r2 = SEditText() inputType TYPE_CLASS_NUMBER
              r2 setText (Controller.groups(i).games(j).getResult._2.toString)
              r2 width (40)
              SButton("Ok", setResult(tables(i), i + 1, Controller.groups(i).games.size - j, r1.getText.toString.toInt, r2.getText.toString.toInt))
            }.wrap
          }
          tables += new STableLayout
          initTable(tables(i), i+1)
          this += tables(i)
          STextView("")
        }
      }}
    } padding 20.dip
  }

  def initTable(table: STableLayout, group: Int): Unit = {
    table.removeAllViews()
    table +=
      new STableRow {
        STextView("Team Name").textColor (Color.WHITE)
        STextView("Points").textColor (Color.WHITE)
        STextView("Goals").textColor (Color.WHITE)
        STextView("Goals Ag").textColor (Color.WHITE)
      }
    Controller.groups(group-1).getTable.reverse.map { team =>
      table +=
        new STableRow {
          STextView(team.name).textColor (Color.WHITE)
          STextView(team.points.toString).textColor (Color.WHITE)
          STextView(team.goals.toString).textColor (Color.WHITE)
          STextView(team.goalsAgainst.toString).textColor (Color.WHITE)
        }
    }
  }

  def setResult(ll: STableLayout, group: Int, game: Int, r1: Int, r2: Int): Unit = {
    Controller.setGameResult(group,game,r1,r2)
    initTable(ll, group)
    if(Controller.groupWinners.size > 0) {
      val winners = Controller.groupWinners.map(x => x._2.name + " has won Group " + x._1.name).toList.mkString("\n")
      toast(winners)
    }
  }

}
