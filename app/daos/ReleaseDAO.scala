package daos

import models.{Project, Release}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import play.db.NamedDatabase
import slick.jdbc.JdbcProfile

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class ReleaseDAO @Inject()(@NamedDatabase("mydb") protected val dbConfigProvider: DatabaseConfigProvider)
                          (implicit ec: ExecutionContext)
  extends HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._

  private val releases = TableQuery[ReleaseTable]

  def getAll(projectId: Int): Future[List[Release]] =
    db.run(releases.filter(_.projectId === projectId).result).map(_.toList)

  def get(id: Int): Future[List[Release]] =
    db.run(releases.filter(_.id === id).result).map(_.toList)

  def add(release: Release): Future[Unit] =
    db.run(releases += release).map(_ => ())


  private class ReleaseTable(tag: Tag) extends Table[Release](tag, "RELEASE") {

    def id: Rep[Int] = column[Int]("id", O.PrimaryKey, O.AutoInc)

    def version: Rep[String] = column[String]("version")

    def link: Rep[String] = column[String]("link")

    def projectId: Rep[Int] = column[Int]("project_id")

    def * = (id.?, version, link, projectId) <> (Release.tupled, Release.unapply)
  }
}
