package com.alex.eko.paragon.utils.abstraction;

import lombok.SneakyThrows;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class AbstractMapper<T extends BaseProperty, D extends BaseProperty> {
    private Class<D> dtoClass;

    @SuppressWarnings("unchecked")
    protected AbstractMapper() {
        this.dtoClass = (Class<D>) ((java.lang.reflect.ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[1];
    }

    public D toDTO(T entity) {
        D dto = createDTO();
        dto.setId(entity.getId());
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setDeleted(entity.getDeleted());
//        dto.setCreated(entity.getCreated());
        mapToDTO(dto, entity);
        return dto;
    }

    public List<D> toDTOs(List<T> entities) {
        return entities.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @SneakyThrows
    private D createDTO() {
        try {
            return dtoClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException("Failed to create DTO instance", e);
        }
    }
    protected abstract void mapToDTO(D dto, T entity);


    protected D mapOptionalFields(T entity, Set<String> fields) {
        // Default implementation does nothing
        return null;
    }


}
