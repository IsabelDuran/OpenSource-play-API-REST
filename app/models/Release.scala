package models

import play.api.libs.json.{Format, Json}

case class Release(id: Option[Int] = None, version: String, link: String, project: Int)

object Release {
  implicit val format: Format[Release] = Json.format
  def tupled = (Release.apply _).tupled
}