package controllers

import javax.inject.Inject
import play.api.mvc._

import scala.concurrent._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */

class ProjectController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def getProject: Action[AnyContent] = Action.async { implicit request =>
    Future.successful(Ok("These are your open source projects: "))
  }
}
