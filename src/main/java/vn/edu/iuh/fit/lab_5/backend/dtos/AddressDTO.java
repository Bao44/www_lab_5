package vn.edu.iuh.fit.lab_5.backend.dtos;

import com.neovisionaries.i18n.CountryCode;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AddressDTO implements Serializable {
    Long id;
    String street;
    String city;
    Short country;
    String number;
    String zipcode;
}