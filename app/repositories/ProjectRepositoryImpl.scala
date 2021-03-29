package repositories

import models.Project
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import play.db.NamedDatabase
import slick.jdbc.JdbcProfile

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class ProjectRepositoryImpl @Inject()(@NamedDatabase("mydb") protected val dbConfigProvider: DatabaseConfigProvider)
                                     (implicit ec: ExecutionContext)
  extends HasDatabaseConfigProvider[JdbcProfile]
    with ProjectRepository {
  import profile.api._

  private val Projects = TableQuery[ProjectTable]

  override def getAll: Future[Seq[Project]] = {
    db.run(Projects.result)
  }

  override def get(id: String): Future[Project] = Future(Project("TODO"))

  private class ProjectTable(tag: Tag) extends Table[Project](tag, "PROJECT") {

    def name = column[String]("name", O.PrimaryKey)

    def * = (name) <> (Project.apply, Project.unapply)
  }
}
