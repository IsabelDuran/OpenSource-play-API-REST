package controllers

import daos.ProjectDAO
import models.Project
import play.api.libs.json.Json
import play.api.mvc._

import javax.inject.Inject
import scala.concurrent._


class ProjectController @Inject()(val controllerComponents: ControllerComponents, projectDAO: ProjectDAO)(
  implicit ec: ExecutionContext) extends BaseController {
  def getProjects: Action[AnyContent] = Action.async { implicit request =>
    projectDAO.getAll.map(project => Ok(Json.toJson(project)))
  }

  def getProjectById(id: Int): Action[AnyContent] = Action.async { implicit request =>
    projectDAO.get(id).map(project => Ok(Json.toJson(project)))
  }

  def findProjectByName(name: Option[String]): Action[AnyContent] = Action.async { implicit request =>
    projectDAO.find(name.getOrElse("")).map(project => Ok(Json.toJson(project)))
  }

  def addProject = Action.async { implicit request =>
    /*
    AnyContentAsJson({"name":"Hello"})
     */
    println(request.body.asJson)
    projectDAO.add(Project(name = "medaigualmeencanta")).map(_ => Ok(Json.toJson("New project created")))
  }


}
