package com.project.shared_calender.common.interfaces;

public interface EntityMapper<E,D> {

    E toEntity(final D dto);

    D toDto(final E entity);
}
