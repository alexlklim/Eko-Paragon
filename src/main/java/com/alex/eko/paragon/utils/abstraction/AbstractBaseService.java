package com.alex.eko.paragon.utils.abstraction;


import com.alex.eko.paragon.utils.exceptions.errors.ResourceNotFoundException;
import com.alex.eko.paragon.utils.security.SH;
import lombok.SneakyThrows;

import java.lang.reflect.Method;
import java.util.List;

public abstract class AbstractBaseService<
        ENTITY extends BaseProperty,
        DTO extends BaseProperty,
        REPO extends BaseRepo<ENTITY, Long>,
        MAP extends AbstractMapper<ENTITY, DTO>> {

    protected final REPO repository;
    protected final MAP mapper;
    private final Class<ENTITY> entityType;

    protected AbstractBaseService(REPO repository, MAP mapper, Class<ENTITY> entityType) {
        this.repository = repository;
        this.mapper = mapper;
        this.entityType = entityType;
    }

    @SneakyThrows
    public DTO create(DTO dto) {
        // Create a new instance of T using reflection
        ENTITY entity = entityType.getDeclaredConstructor().newInstance();
        // AOP will handle createdBy and deleted fields automatically
        updateEntityFromDTO(entity, dto);
        ENTITY savedEntity = repository.save(entity);  // Save the entity
        return mapper.toDTO(savedEntity); // Convert to DTO and return
    }

    @SneakyThrows
    public DTO update(DTO dto) {
        ENTITY entity = getByIdFromRepository(dto.getId());
        // AOP will handle updates to deleted field if necessary
        updateEntityFromDTO(entity, dto);
        ENTITY updatedEntity = repository.save(entity);  // Save the updated entity
        return mapper.toDTO(updatedEntity); // Convert to DTO and return
    }

    @SneakyThrows
    protected void isUserHaveOwnerPermission(ENTITY entity) {
        Method getCreatedByMethod = entity.getClass().getMethod("getCreatedBy");
        Object createdBy = getCreatedByMethod.invoke(entity);
        if (!createdBy.equals(SH.getUserId())) {
            throw new ResourceNotFoundException("Entity doesn't belong to the current user");
        }
    }

    protected abstract void updateEntityFromDTO(ENTITY entity, DTO dto);

    @SneakyThrows
    public ENTITY getByIdFromRepository(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id: " + id));
    }

    @SneakyThrows
    public ENTITY getActiveById(Long id) {
        return repository.findByIdAndDeleted(id, true)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id: " + id));
    }

    @SneakyThrows
    public DTO getDTOById(Long id) {
        ENTITY entity = getByIdFromRepository(id);
        return mapper.toDTO(entity);
    }

    public List<DTO> getAllDTOs(Boolean deleted) {
        List<ENTITY> entities = repository.findAllByDeleted(deleted);
        return mapper.toDTOs(entities);
    }

}
