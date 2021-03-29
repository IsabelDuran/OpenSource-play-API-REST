package models

import play.api.libs.json.{Format, Json}

case class Project(name: String)

object Project {
  implicit val format: Format[Project] = Json.format
}