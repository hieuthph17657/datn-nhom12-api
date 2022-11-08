package datnnhom12api.repository;

import datnnhom12api.entity.OrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, Long>, JpaSpecificationExecutor<OrderDetailEntity> {
    @Query("SELECT o FROM OrderDetailEntity o WHERE o.order.id=?1 ")
    public List<OrderDetailEntity> findByOrder(Long id);
}
