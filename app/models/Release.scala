package models

import play.api.libs.json.{Format, Json}

case class Release(version: String, link: String)

object Release {
  implicit val format: Format[Project] = Json.format
}