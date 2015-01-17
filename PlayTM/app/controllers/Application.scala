package controllers

import models.{SR, GroupsTeams, InitForm}
import play.api.data._
import play.api.data.Forms._
import play.api.mvc._

object Application extends Controller {
  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def teamsAndGroups = Action {
    Ok(views.html.teamsAndGroups(InitForm(1,1)))
  }

  val initForm: Form[InitForm] = Form {
    mapping(
      "Teams" -> number,
      "Groups" -> number
    )(InitForm.apply)(InitForm.unapply)
  }

  def postInit = Action { implicit request =>
    val init = initForm.bindFromRequest.get
    Ok(views.html.teamsAndGroups(init))
  }

  val teamsgroups: Form[GroupsTeams] = Form {
    mapping(
      "groups" -> list(text),
      "teams" -> list(text)
    )(GroupsTeams.apply)(GroupsTeams.unapply)
  }

  def initTnG = Action { implicit request =>
    val init = teamsgroups.bindFromRequest.get
    println(init.groups.size)
    init.teams.foreach(x => Controller.initTeams(x))
    init.groups.foreach(x => Controller.initGroups(init.groups.size, x))
    println(Controller.groups.size)
    Ok(views.html.mainView(Controller.groups.toList))
  }

  val sr: Form[SR] = Form {
    mapping(
      "group" -> number,
      "game" -> number,
      "r1" -> number,
      "r2" -> number
    )(SR.apply)(SR.unapply)
  }

  def setResult = Action { implicit request =>
    val init = sr.bindFromRequest.get
    println(init.game + " " +init.group)
    Controller.setGameResult(init.group, init.game, init.r1, init.r2)
    Controller.groups.foreach(x => x.games.foreach(y => println(y.toString)))
    Redirect(routes.Application.initTnG())
  }

}