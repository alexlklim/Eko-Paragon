package com.alex.eko.paragon.company.service;

import com.alex.eko.paragon.company.domain.CashRegister;
import com.alex.eko.paragon.company.domain.Company;
import com.alex.eko.paragon.company.domain.Employee;
import com.alex.eko.paragon.company.dto.EmployeeDTO;
import com.alex.eko.paragon.company.mapper.EmployeeMapper;
import com.alex.eko.paragon.company.repo.CompanyRepo;
import com.alex.eko.paragon.company.repo.EmployeeRepo;
import com.alex.eko.paragon.image.ImageService;
import com.alex.eko.paragon.security.UserService;
import com.alex.eko.paragon.security.domain.Role;
import com.alex.eko.paragon.security.repo.SH;
import com.alex.eko.paragon.utils.abstraction.AbstractBaseService;
import com.alex.eko.paragon.utils.exceptions.errors.ResourceNotFoundException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.alex.eko.paragon.utils.functional.UpdateUtil.updateIfNotNull;


@Service
public class EmployeeService
        extends AbstractBaseService<Employee, EmployeeDTO, EmployeeRepo, EmployeeMapper> {
    private final CompanyRepo companyRepo;
    private final ImageService imageService;
    private final CashRegisterService cashRegisterService;

    private final UserService userService;


    @Autowired
    public EmployeeService(EmployeeRepo repository, EmployeeMapper mapper, CompanyRepo companyRepo, ImageService employeeService, CashRegisterService cashRegisterService, UserService userService) {
        super(repository, mapper, Employee.class);
        this.companyRepo = companyRepo;
        this.imageService = employeeService;
        this.cashRegisterService = cashRegisterService;
        this.userService = userService;
    }


    @Override
    protected void updateEntityFromDTO(Employee entity, EmployeeDTO dto) {
        updateIfNotNull(dto.getFirstName(), entity::setFirstName);
        updateIfNotNull(dto.getLastName(), entity::setLastName);
        updateIfNotNull(dto.getEmail(), entity::setEmail);
        updateIfNotNull(dto.getPhoneNumber(), entity::setPhoneNumber);
        updateIfNotNull(dto.getAddress(), entity::setAddress);
        //   update image id
        if (dto.getProfileImageId() != null) {
            entity.setProfileImage(imageService.getFileById(dto.getProfileImageId()));
        }

        updateIfNotNull(dto.getCashierIdentification(), entity::setCashierIdentification);

        // set the company current user belongs to
        entity.setCompany(companyRepo.findByOwnerIdAndDeleted(SH.getUserId(), false)
                .orElseThrow(() -> new RuntimeException("Company not found")));

        // set the user who can access be the employee
        // add role EMP to this user
        updateIfNotNull(dto.getBelongToUserId(), entity::setBelongToUserId);
        userService.addRoleToUser(dto.getBelongToUserId(), Role.EMP);

    }


    @SneakyThrows
    public List<EmployeeDTO> getAllByCompany(Boolean deleted) {
        Company company = companyRepo.findByOwnerIdAndDeleted(SH.getUserId(), deleted)
                .orElseThrow(() -> new RuntimeException("Company not found"));
        return mapper.toDTOs(repository.findByCompany(company.getId(), deleted));

    }
}
