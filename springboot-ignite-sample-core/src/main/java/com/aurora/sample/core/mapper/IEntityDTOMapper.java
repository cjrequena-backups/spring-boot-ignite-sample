
package com.aurora.sample.core.mapper;

import java.util.List;

/**
 * <p>
 * <p>
 * <p>
 * <p>
 * @author cjrequena
 * @version 1.0
 * @since JDK1.8
 * @see
 *
 */
public interface IEntityDTOMapper<D, E> {

    /**
     * To dto.
     *
     * @param entity the entity
     * @return the dto
     */
    D toDto(E entity);

    /**
     * To entity.
     *
     * @param dto the dto
     * @return the e
     */
    E toEntity(D dto);

    /**
     * To dtos.
     *
     * @param entities the entities
     * @return the list
     */
    List<D> toDtos(List<E> entities);

    /**
     * To entities.
     *
     * @param dtos the dtos
     * @return the list
     */
    List<E> toEntities(List<D> dtos);
}
