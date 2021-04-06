package controllers

import daos.ProjectDAO
import models.Project
import play.api.libs.json._
import play.api.mvc._

import javax.inject.Inject
import scala.concurrent._


class ProjectController @Inject()(val controllerComponents: ControllerComponents, projectDAO: ProjectDAO)(
  implicit ec: ExecutionContext) extends BaseController {
  def getProjects: Action[AnyContent] = Action.async { implicit request =>
    projectDAO.getAll
      .map(projects => Ok(Json.toJson(projects)))
  }

  def getProjectById(id: Int): Action[AnyContent] = Action.async { implicit request =>
    projectDAO.get(id)
      .map(project => Ok(Json.toJson(project)))
  }

  def findProjectByName(name: Option[String]): Action[AnyContent] = Action.async { implicit request =>
    projectDAO.find(name.getOrElse(""))
      .map(project => Ok(Json.toJson(project)))
  }

  def addProject: Action[AnyContent] = Action.async { implicit request =>
    val jsValue = request.body.asJson.get
    jsValue.toString()
    jsValue match {
      case JsString(value) =>  projectDAO
                              .add(Project(name = value))
                              .map(_ => Ok(Json.toJson(s"New project created with name $value")))
      case JsNumber(value)  => projectDAO
                              .add(Project(name = value.toString()))
                              .map(_ => Ok(Json.toJson(s"New project created with name ${value.toString}")))

      case JsObject(value)  => projectDAO.add(Project(name = value.toList.head._2.toString())).map(_ => Ok(s"New project created with name ${value.toList.head._2}"))

      case _                => Future(BadRequest)
    }

  }

}
