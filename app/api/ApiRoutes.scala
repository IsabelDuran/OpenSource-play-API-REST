package api

import controllers.ProjectController

import javax.inject.Inject
import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.api.routing.sird._

class ApiRoutes @Inject()(projectController: ProjectController) extends SimpleRouter {
  override def routes: Routes = {
    case GET(p"/") => projectController.getProject
    //case GET(p"/$id") => projectController.findProjectId(id)
  }
}
