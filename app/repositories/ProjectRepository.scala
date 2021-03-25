package repositories

import com.google.inject.ImplementedBy
import models.Project

import scala.concurrent.Future

@ImplementedBy(classOf[ProjectRepositoryImpl])
trait ProjectRepository {
  def getAll: Future[List[Project]]
}
