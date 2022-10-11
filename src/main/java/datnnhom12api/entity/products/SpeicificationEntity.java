package datnnhom12api.entity.products;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Table(name = "speicifications")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class SpeicificationEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id")
    private Long product;

    @Column(name = "debut")
    private Date debut;

    @Column(name = "insurance")
    private int insurance;

    @Column(name = "origin")
    private String origin;

    @Column(name = "modal")
    private String modal;

    @Column(name = "p_n ")
    private String p_n;

}
