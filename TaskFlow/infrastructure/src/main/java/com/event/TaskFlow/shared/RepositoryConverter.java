package com.event.TaskFlow.shared;

import jakarta.validation.constraints.NotNull;

public interface RepositoryConverter<T , P> {

    default T mapToTable (@NotNull final P persistence) {
        throw new UnsupportedOperationException();
    }

    default P mapToEntity(@NotNull final T object) {
        throw new UnsupportedOperationException();
    }
}
