package repositories

import models.Project

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class ProjectRepositoryImpl @Inject()()(implicit ec: ExecutionContext) extends ProjectRepository {
  override def getAll: Future[List[Project]] = {
    Future(List(Project("Example project")))
  }
}
