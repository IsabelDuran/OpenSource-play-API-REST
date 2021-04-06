package daos

import models.Project
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import play.db.NamedDatabase
import slick.jdbc.JdbcProfile

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class ProjectDAO @Inject()(@NamedDatabase("mydb") protected val dbConfigProvider: DatabaseConfigProvider)
                          (implicit ec: ExecutionContext)
  extends HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._

  private val projects = TableQuery[ProjectTable]

  def getAll: Future[List[Project]] =
    db.run(projects.result).map(_.toList)


  def get(id: Int): Future[List[Project]] =
    db.run(projects.filter(_.id === id).result).map(_.toList)


  def find(name: String): Future[List[Project]] =
    db.run(projects.filter(_.name === name).result).map(_.toList)

  def add(project: Project): Future[Unit] =
    db.run(projects += project).map(_ => ())

  private class ProjectTable(tag: Tag) extends Table[Project](tag, "PROJECT") {

    def id: Rep[Int] = column[Int]("id", O.PrimaryKey, O.AutoInc)

    def name: Rep[String] = column[String]("name")

    def * = (id.?, name) <> (Project.tupled, Project.unapply)
  }
}
