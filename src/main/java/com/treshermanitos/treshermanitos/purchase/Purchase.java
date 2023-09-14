package com.treshermanitos.treshermanitos.purchase;

import com.treshermanitos.treshermanitos.customer.Customer;
import com.treshermanitos.treshermanitos.privilege.Privilege;
import com.treshermanitos.treshermanitos.product.Product;
import com.treshermanitos.treshermanitos.purchase.projections.PurchaseProjectionClass;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "`purchase`")
@NamedNativeQuery(
        name = "getPurchaseDTOCustom",
        query = "SELECT p.id AS purchase_id, p.payment AS payment, " +
                "c.id AS customer_id, c.dni AS dni, c.addres AS addres, " +
                "u.id AS user_id, u.firstName AS firstName, u.lastName AS lastName " +
                "FROM Purchase p " +
                "JOIN Customer c ON p.customerId = c.id " +
                "JOIN User u ON c.userId = u.id " +
                "WHERE p.id = :id ",
        resultSetMapping = "PurchaseDTOCustomMapping"
)
@SqlResultSetMapping(
        name = "PurchaseDTOCustomMapping",
        classes = @ConstructorResult(
                targetClass = PurchaseDTOCustom.class,
                columns = {
                        @ColumnResult(name = "purchase_id", type = Long.class),
                        @ColumnResult(name = "payment", type = PaymentMethod.class),
                        @ColumnResult(name = "customer_id", type = Long.class),
                        @ColumnResult(name = "dni", type = String.class),
                        @ColumnResult(name = "addres", type = String.class),
                        @ColumnResult(name = "user_id", type = Long.class),
                        @ColumnResult(name = "firstName", type = String.class),
                        @ColumnResult(name = "lastName", type = String.class),
                }
        )
)
@NamedNativeQuery(
        name = "getPurchasesDTOCustom",
        query = "SELECT " +
                "p.id AS id, " +
                "p.payment AS payment, " +
                "customer.id AS customerId, " +
                "customer.dni AS dni, " +
                "customer.addres AS addres, " +
                "u.id AS userId, " +
                "u.firstName AS firstName, " +
                "u.lastName AS lastName, " +
                "GROUP_CONCAT(CONCAT(products.id, ':', products.name) SEPARATOR ', ') AS products " +
                "FROM Purchase p " +
                "JOIN Customer customer ON p.customerId = customer.id " +
                "JOIN User u ON customer.userId = u.id " +
                "LEFT JOIN Purchases_Products pp ON p.id = pp.purchaseId " +
                "LEFT JOIN Product products ON pp.productId = products.id " +
                "GROUP BY p.id",
        resultSetMapping = "PurchasesDTOCustomMapping"
)
@SqlResultSetMapping(
        name = "PurchasesDTOCustomMapping",
        classes = @ConstructorResult(
                targetClass = PurchaseProjectionClass.class,
                columns = {
                        @ColumnResult(name = "id", type = Long.class),
                        @ColumnResult(name = "payment", type = PaymentMethod.class),
                        @ColumnResult(name = "customerId", type = Long.class),
                        @ColumnResult(name = "dni", type = String.class),
                        @ColumnResult(name = "addres", type = String.class),
                        @ColumnResult(name = "userId", type = Long.class),
                        @ColumnResult(name = "firstName", type = String.class),
                        @ColumnResult(name = "lastName", type = String.class),
                        @ColumnResult(name = "products", type = String.class)
                }
        )
)
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PaymentMethod payment;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

    private String state;

    @Column(columnDefinition = "TIMESTAMP", name = "createdAt")
    private Date createdAt;
    @Column(columnDefinition = "TIMESTAMP", name = "updatedAt")
    private Date updatedAt;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "purchases_products",
            joinColumns = @JoinColumn(
                    name = "purchaseId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "productId", referencedColumnName = "id"))
    private List<Product> products;

}
