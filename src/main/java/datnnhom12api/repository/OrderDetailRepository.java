package datnnhom12api.repository;

import datnnhom12api.entity.OrderDetailEntity;
import datnnhom12api.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, Long>,
        JpaSpecificationExecutor<OrderDetailEntity> {
    @Query("select o from OrderDetailEntity o where o.order.id = :Id")
    List<OrderDetailEntity>getOrderDetailEntityById(@Param("Id")Long id);
}
