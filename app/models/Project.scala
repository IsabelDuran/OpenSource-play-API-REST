package models

import play.api.libs.json.{Format, Json}

/**
 * DTO for Project information.
 */
case class Project(name: String)

object Project {
  /**
   * Mapping to read/write a PostResource out as a JSON value.
   */
  implicit val format: Format[Project] = Json.format
}