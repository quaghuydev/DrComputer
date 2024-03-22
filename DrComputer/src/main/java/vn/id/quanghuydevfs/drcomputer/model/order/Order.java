package vn.id.quanghuydevfs.drcomputer.model.order;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.id.quanghuydevfs.drcomputer.model.user.User;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String customer;
    private String street;
    private String province;
    private String district;
    private String ward;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private User user;
}
