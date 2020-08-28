package vn.javisco.agency.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_order")
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Enumerated(EnumType.STRING)
    private OrderStatusFlag status;

    @ManyToOne
    @JoinColumn(name = "user_login_id")
    @JsonIgnore
    private UserLogin user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    private List<OrderItem> items;
}
