package controllers

import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}
import repositories.ProjectRepository

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class ReleaseController @Inject()(val controllerComponents: ControllerComponents, projectRepository: ProjectRepository)(
  implicit ec: ExecutionContext) extends BaseController {
  def getAllProjectReleases: Action[AnyContent] = Action.async { implicit request =>
    Future.successful(Ok("TODO"))
  }

  def getRelease(version: String): Action[AnyContent] = Action.async { implicit request =>
    Future.successful(Ok("TODO"))
  }

  def addRelease: Action[AnyContent] = Action.async { implicit request =>
    Future.successful(Ok("TODO"))
  }
}