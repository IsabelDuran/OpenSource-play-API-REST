package controllers

import play.api.mvc.{BaseController, ControllerComponents}

import javax.inject.Inject
import scala.concurrent.ExecutionContext

class AppController @Inject()(val controllerComponents: ControllerComponents)
                             (implicit assetsFinder: AssetsFinder)
                              extends BaseController {

  def index = Action {
    Ok(views.html.index("home page"))
  }
}
