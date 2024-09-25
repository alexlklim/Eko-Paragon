package com.alex.eko.paragon.utils.aspect;


import com.alex.eko.paragon.utils.abstraction.BaseProperty;
import com.alex.eko.paragon.utils.security.SH;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class EntityAuditAspect {

    @Before("execution(* org.springframework.data.jpa.repository.JpaRepository+.save(..)) && args(entity)")
    public void beforeSave(BaseProperty entity) {
        if (entity.getId() == null) {
            entity.setCreatedBy(SH.getUserId());
            entity.setDeleted(false);
        }
    }
}