package datnnhom12api.repository;

import datnnhom12api.entity.OrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, Long>, JpaSpecificationExecutor<OrderDetailEntity> {
}
