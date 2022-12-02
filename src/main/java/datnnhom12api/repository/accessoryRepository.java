package datnnhom12api.repository;

import datnnhom12api.entity.accessoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface accessoryRepository extends JpaRepository<accessoryEntity, Long>, JpaSpecificationExecutor<accessoryEntity> {
}
