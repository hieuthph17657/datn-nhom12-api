package datnnhom12api.repository;

import datnnhom12api.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long>, JpaSpecificationExecutor<OrderEntity> {
    @Query("SELECT o FROM OrderEntity o WHERE o.user.username=?1 ORDER BY o.id desc ")
    public List<OrderEntity> findUserName(String username);

    @Query("SELECT o FROM OrderEntity o WHERE o.createdAt=?1 ORDER BY o.id desc")
    public List<OrderEntity> findByDate(LocalDateTime createdAt);
}
