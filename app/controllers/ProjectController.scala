package controllers

import play.api.libs.json.Json

import javax.inject.Inject
import play.api.mvc._
import repositories.ProjectRepository

import scala.concurrent._


class ProjectController @Inject()(val controllerComponents: ControllerComponents, projectRepository: ProjectRepository)(
  implicit ec: ExecutionContext) extends BaseController {
  def getProjects: Action[AnyContent] = Action.async { implicit request =>
    projectRepository.getAll.map(project => Ok(Json.toJson(project)))
  }

  def getProjectById(id: String): Action[AnyContent] = Action.async { implicit request =>
    Future.successful(Ok("TODO"))
  }

  def findProjectByName(name: String): Action[AnyContent] = Action.async { implicit request =>
    Future.successful(Ok("TODO"))
  }

  def addProject: Action[AnyContent] = Action.async { implicit request =>
    Future.successful(Ok("TODO"))
  }


}
