package api

import controllers.{ProjectController, ReleaseController}

import javax.inject.Inject
import play.api.routing.Router.Routes
import play.api.routing._
import play.api.routing.sird._


class ApiRoutes @Inject()(projectController: ProjectController, releaseController: ReleaseController) extends SimpleRouter {
  override def routes: Routes = {
    case GET(p"/")       =>
      projectController.getProjects

    case GET(p"/find" ? q_?"name=$name") =>
      projectController.findProjectByName(name)

    case GET(p"/$id")   =>
      projectController.getProjectById(id.toInt)

    case POST(p"/")      =>
      projectController.addProject

    case GET(p"/$projectId/releases") =>
      releaseController.getAllProjectReleases(projectId.toInt)

    case GET(p"/releases/$id") =>
      releaseController.getRelease(id.toInt)

    case POST(p"/$projectId/releases") =>
      releaseController.addRelease(projectId.toInt)
  }
}
