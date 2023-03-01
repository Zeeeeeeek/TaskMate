package me.zeeeeeeek.backend.util;

/**
 * This interface defines the contract for a DTO mapper.
 * @param <T> the type of the entity to map from
 * @param <D> the type of the DTO to map to
 */
public interface DTOMapper<T, D> {

        /**
        * Maps an entity to a DTO.
        * @param entity the entity to map
        * @return the DTO
        * @throws NullPointerException if the entity is null
        */
        D mapToDTO(T entity);
}
