package com.alex.eko.paragon.company.domain;


import com.alex.eko.paragon.company.domain.enums.EmployeeRole;
import com.alex.eko.paragon.image.Image;
import com.alex.eko.paragon.utils.abstraction.BaseEntity;
import com.alex.eko.paragon.utils.abstraction.BaseProperty;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder(toBuilder = true)
@Table(name = "employees")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Employee
        extends BaseEntity
        implements BaseProperty {


    @Column(name = "belong_to_user_id")
    Long belongToUserId;


    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "email")
    String email;

    @Column(name = "phone_number")
    String phoneNumber;

    @Column(name = "address")
    String address;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_image_id")
    Image profileImage;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    Company company;


    @Column(name = "cashier_identification")
    String cashierIdentification;


    @ManyToMany
    @JoinTable(
            name = "employees_cash_registers",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "cash_register_id")
    )
    List<CashRegister> cashRegisters;


}
