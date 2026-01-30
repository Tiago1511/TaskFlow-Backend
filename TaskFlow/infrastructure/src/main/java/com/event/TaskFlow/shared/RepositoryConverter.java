package com.event.TaskFlow.shared;

import java.io.Serializable;

public interface RepositoryConverter<T , P> {

    default T mapToTable (final P persistence) {
        throw new UnsupportedOperationException();
    }

    default P mapToEntity(final T object) {
        throw new UnsupportedOperationException();
    }
}
