package datnnhom12api.repository;

import datnnhom12api.entity.OrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, Long>,
        JpaSpecificationExecutor<OrderDetailEntity> {
    @Query("SELECT o FROM OrderDetailEntity o WHERE o.order.id=?1 ")
    List<OrderDetailEntity> findByOrder(Long id);

    @Query("select o from OrderDetailEntity o where o.order.id = :Id")
    List<OrderDetailEntity> getOrderDetailEntityById(@Param("Id") Long id);

    @Query("select o from OrderDetailEntity  o where  o.order.id = :orderId and o.product.id = :productId")
    OrderDetailEntity getOrderDetailEntityByOrderIsAndAndProduct(@Param("orderId")Long orderId,
                                                                 @Param("productId")Long productId);
}
