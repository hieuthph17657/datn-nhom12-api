package datnnhom12api.repository;

import datnnhom12api.entity.ProductEntity;
import datnnhom12api.entity.ReturnEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ReturnRepository extends JpaRepository<ReturnEntity, Long>, JpaSpecificationExecutor<ReturnEntity> {
}