package api

import controllers.{ProjectController, ReleaseController}

import javax.inject.Inject
import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.api.routing.sird._

class ApiRoutes @Inject()(projectController: ProjectController, releaseController: ReleaseController) extends SimpleRouter {
  override def routes: Routes = {
    case GET(p"/")       =>
      projectController.getProjects

    case GET(p"/$id")   =>
      projectController.getProjectById(id.toInt)

    case GET(p"/find" ? q_?"name=$name") =>
      projectController.findProjectByName(name)

    case POST(p"/")      =>
      projectController.addProject

    case GET(p"/releases") =>
      releaseController.getAllProjectReleases

    case GET(p"/releases/$version") =>
      releaseController.getRelease(version)

    case POST(p"/releases") =>
      releaseController.addRelease



  }
}
