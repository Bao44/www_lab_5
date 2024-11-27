package vn.edu.iuh.fit.lab_5.backend.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address", schema = "works")
public class Address {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "street", length = 150)
    @NonNull
    private String street;

    @Column(name = "city", length = 50)
    @NonNull
    private String city;

    @Column(name = "country")
    @NonNull
    private Short country;

    @Column(name = "number", length = 20)
    @NonNull
    private String number;

    @Column(name = "zipcode", length = 7)
    @NonNull
    private String zipcode;

    public Address(String street, String city, Short country, String number, String zipcode) {
        this.street = street;
        this.city = city;
        this.country = country;
        this.number = number;
        this.zipcode = zipcode;
    }
}