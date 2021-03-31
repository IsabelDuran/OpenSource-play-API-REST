package controllers

import daos.ReleaseDAO
import models.Release
import play.api.libs.json.Json
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class ReleaseController @Inject()(val controllerComponents: ControllerComponents, releaseDAO: ReleaseDAO)(
  implicit ec: ExecutionContext) extends BaseController {

  def getAllProjectReleases(projectId: Int): Action[AnyContent] = Action.async { implicit request =>
    releaseDAO.getAll(projectId)
      .map(releases => Ok(Json.toJson(releases)))
  }

  def getRelease(id: Int): Action[AnyContent] = Action.async { implicit request =>
    releaseDAO.get(id)
      .map(releases => Ok(Json.toJson(releases)))
  }

  def addRelease(projectId: Int): Action[AnyContent] = Action.async { implicit request =>
    val versionParam = (request.body.asJson.get \ "version").as[String]
    val linkParam = (request.body.asJson.get \ "link").as[String]
    releaseDAO
      .add(Release(version = versionParam, link = linkParam, project = projectId))
      .map(_ => Ok(Json.toJson("New release created")))
  }
}