package models

import play.api.libs.json.{Format, Json}

case class Project(id: Option[Int] = None, name: String)

object Project {
  implicit val format: Format[Project] = Json.format
  def tupled = (Project.apply _).tupled
}