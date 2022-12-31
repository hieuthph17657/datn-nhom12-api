package datnnhom12api.repository;

import datnnhom12api.dto.StatisticalMonthDTO;
import datnnhom12api.dto.StatisticalOrderDTO;
import datnnhom12api.dto.StatisticalProductDTO;
import datnnhom12api.dto.SumProductDTO;
import datnnhom12api.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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



    @Query("SELECT c FROM OrderEntity c WHERE (c.createdAt BETWEEN ?1 AND ?2) AND (c.createdAt BETWEEN ?1 AND ?2)")
    Page<OrderEntity> betweenDate(LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.status = ?1")
    Page<OrderEntity> findAllAndStatus(String searchStatus, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.status = ?1 AND c.customerName like %?2%")
    Page<OrderEntity> searchNameAndStatus(String searchStatus, String searchName, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.status = ?1 AND c.phone = ?2")
    Page<OrderEntity> searchPhoneAndStatus(String searchStatus, String searchPhone, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.phone = ?1")
    Page<OrderEntity> searchPhone(String searchPhone, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.customerName like %?1%")
    Page<OrderEntity> searchName(String searchName, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.payment = ?1")
    Page<OrderEntity> searchPayment(String searchPayment, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.status = ?1 AND c.payment = ?2")
    Page<OrderEntity> searchPaymentAndStatus(String searchStatus, String searchPayment, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.status = ?1 AND (c.createdAt BETWEEN ?2 AND ?3) AND (c.createdAt BETWEEN ?2 AND ?3)")
    Page<OrderEntity> betweenDateAndStatus(String searchStatus, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.phone = ?1 AND (c.createdAt BETWEEN ?2 AND ?3) AND (c.createdAt BETWEEN ?2 AND ?3)")
    Page<OrderEntity> betweenDateAndPhone(String searchPhone, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.status = ?1 AND c.phone = ?2 AND (c.createdAt BETWEEN ?3 AND ?4) AND (c.createdAt BETWEEN ?3 AND ?4)")
    Page<OrderEntity> betweenDateAndPhoneAndStatus(String searchStatus, String searchPhone, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.status = ?1 AND c.payment = ?2 AND (c.createdAt BETWEEN ?3 AND ?4) AND (c.createdAt BETWEEN ?3 AND ?4)")
    Page<OrderEntity> betweenDateAndPaymentAndStatus(String searchStatus, String searchPayment, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.payment = ?1 AND (c.createdAt BETWEEN ?2 AND ?3) AND (c.createdAt BETWEEN ?2 AND ?3)")
    Page<OrderEntity> betweenDateAndPayment(String searchPayment, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.status = ?1 AND c.customerName like %?2% AND (c.createdAt BETWEEN ?3 AND ?4) AND (c.createdAt BETWEEN ?3 AND ?4)")
    Page<OrderEntity> betweenDateAndNameAndStatus(String searchStatus, String searchName, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.customerName like %?1% AND (c.createdAt BETWEEN ?2 AND ?3) AND (c.createdAt BETWEEN ?2 AND ?3)")
    Page<OrderEntity> betweenDateAndName(String searchName, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.payment = ?1 AND c.customerName like %?2% AND c.status = ?3 AND c.phone = ?4 AND (c.createdAt BETWEEN ?5 AND ?6) AND (c.createdAt BETWEEN ?5 AND ?6)")
    Page<OrderEntity> betweenDateAndPhoneAndStatusAndPaymentAndName(String searchPayment, String searchName, String searchStatus, String searchPhone, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.payment = ?1 AND c.customerName like %?2% AND c.phone = ?3 AND (c.createdAt BETWEEN ?4 AND ?5) AND (c.createdAt BETWEEN ?4 AND ?5)")
    Page<OrderEntity> betweenDateAndPhoneAndPaymentAndName(String searchPayment, String searchName, String searchPhone, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.payment = ?1 AND c.status = ?2 AND c.phone = ?3 AND (c.createdAt BETWEEN ?4 AND ?5) AND (c.createdAt BETWEEN ?4 AND ?5)")
    Page<OrderEntity> betweenDateAndPhoneAndStatusAndPayment(String searchPayment, String searchStatus, String searchPhone, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.payment = ?1 AND c.phone = ?2 AND (c.createdAt BETWEEN ?3 AND ?4) AND (c.createdAt BETWEEN ?3 AND ?4)")
    Page<OrderEntity> betweenDateAndPhoneAndPayment(String searchPayment, String searchPhone, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.payment = ?1 AND c.status = ?2 AND c.customerName like %?3% AND (c.createdAt BETWEEN ?4 AND ?5) AND (c.createdAt BETWEEN ?4 AND ?5)")
    Page<OrderEntity> betweenDateAndNameAndStatusAndPayment(String searchPayment, String searchStatus, String searchName, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.payment = ?1 AND c.customerName like %?2% AND (c.createdAt BETWEEN ?3 AND ?4) AND (c.createdAt BETWEEN ?3 AND ?4)")
    Page<OrderEntity> betweenDateAndNameAndPayment(String searchPayment, String searchName, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.customerName like %?1% AND c.status = ?2 AND c.phone = ?3 AND (c.createdAt BETWEEN ?4 AND ?5) AND (c.createdAt BETWEEN ?4 AND ?5)")
    Page<OrderEntity> betweenDateAndPhoneAndStatusAndName(String searchName, String searchStatus, String searchPhone, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

    @Query("SELECT c FROM OrderEntity c WHERE c.customerName like %?1% AND c.phone = ?2 AND (c.createdAt BETWEEN ?3 AND ?4) AND (c.createdAt BETWEEN ?3 AND ?4)")
    Page<OrderEntity> betweenDateAndPhoneAndName(String searchName, String searchPhone, LocalDateTime startDate, LocalDateTime endDate, Specification<OrderEntity> specifications, Pageable pageable);

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

    @Query("SELECT new SumProductDTO(sum (od.quantity)) FROM ProductEntity p inner join OrderDetailEntity" +
            " od on od.product.id = p.id" +
            " inner join OrderEntity o on od.order.id = o.id where  o.status = 'DA_NHAN'")
    SumProductDTO numberOfProductsSold();

    @Query("SELECT new SumProductDTO(count (c.id))FROM OrderEntity c")
    SumProductDTO countOrder();
}
