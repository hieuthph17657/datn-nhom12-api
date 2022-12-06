package datnnhom12api.repository;

import datnnhom12api.dto.StatisticalMonthDTO;
import datnnhom12api.dto.StatisticalOrderDTO;
import datnnhom12api.dto.StatisticalProductDTO;
import datnnhom12api.dto.SumProductDTO;
import datnnhom12api.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long>, JpaSpecificationExecutor<OrderEntity> {
    @Query("SELECT o FROM OrderEntity o WHERE o.user.username=?1 ORDER BY o.id desc ")
    public List<OrderEntity> findUserName(String username);

    @Query("SELECT o FROM OrderEntity o WHERE o.createdAt=?1 ORDER BY o.id desc")
    public List<OrderEntity> findByDate(LocalDateTime createdAt);


    @Query("SELECT c FROM OrderEntity c WHERE (c.createdAt BETWEEN ?1 AND ?2) AND c.createdAt BETWEEN ?1 AND ?2 ")
    Page<OrderEntity> betweenDate(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);


    @Query("SELECT new StatisticalMonthDTO(extract(month from c.updatedAt), sum (c.total))  FROM OrderEntity c WHERE c.status='DA_NHAN' " +
            "AND extract(YEAR from c.updatedAt) = :year group by extract(month from c.updatedAt)")
    List<StatisticalMonthDTO> statisticalByYear(@Param("year") Integer year);


    @Query("SELECT new StatisticalOrderDTO((c.status),count(c.id))  FROM OrderEntity c WHERE extract(month from c.createdAt) = :month " +
            "and extract(YEAR from c.createdAt) = :year group by c.status")
    List<StatisticalOrderDTO> statisticalByOrder(@Param("month") Integer month, @Param("year") Integer year);


    @Query("SELECT new StatisticalProductDTO(p.id, concat(p.name, ' ', p.debut), count (p.quantity)) FROM ProductEntity p inner join OrderDetailEntity" +
            " od on od.product.id = p.id" +
            " inner join OrderEntity o on od.order.id = o.id where  o.status = 'DA_NHAN' group by p.id")
    List<StatisticalProductDTO> statisticalByProduct();

    @Query("SELECT new SumProductDTO(count (c.id))FROM OrderEntity c")
    SumProductDTO countOrder();
}
