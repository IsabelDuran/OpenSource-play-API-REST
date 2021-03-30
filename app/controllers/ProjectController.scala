package controllers

import play.api.libs.json.Json

import javax.inject.Inject
import play.api.mvc._
import daos.ProjectDAO
import models.Project

import scala.concurrent._


class ProjectController @Inject()(val controllerComponents: ControllerComponents, projectDAO: ProjectDAO)(
  implicit ec: ExecutionContext) extends BaseController {
  def getProjects: Action[AnyContent] = Action.async { implicit request =>
    projectDAO.getAll.map(project => Ok(Json.toJson(project)))
  }

  def getProjectById(id: Int): Action[AnyContent] = Action.async { implicit request =>
    projectDAO.get(id).map(project => Ok(Json.toJson(project)))
  }

  def findProjectByName(name: String): Action[AnyContent] = Action.async { implicit request =>
    Future.successful(Ok("TODO"))
  }

  def addProject = Action.async { implicit request =>
    /*
    AnyContentAsJson({"name":"Hello"})
     */
    println(request.body)
    projectDAO.add(Project(1, "hi")).map(_ => Ok(Json.toJson("New project created")))
  }


}
