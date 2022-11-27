package datnnhom12api.repository;

import datnnhom12api.entity.GraphicsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface GraphicsRepository extends JpaRepository<GraphicsEntity, Long>, JpaSpecificationExecutor<GraphicsEntity> {
}
