package controllers

import play.api.libs.json.Json

import javax.inject.Inject
import play.api.mvc._
import repositories.ProjectRepository

import scala.concurrent._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */

class ProjectController @Inject()(val controllerComponents: ControllerComponents, projectRepository: ProjectRepository)(
  implicit ec: ExecutionContext) extends BaseController {

  def getProject: Action[AnyContent] = Action.async { implicit request =>
    projectRepository.getAll.map(a => Ok(Json.toJson(a)))
  }
}
