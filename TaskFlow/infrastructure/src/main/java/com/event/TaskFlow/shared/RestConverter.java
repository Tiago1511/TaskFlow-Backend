package com.event.TaskFlow.shared;

import java.io.Serializable;

public interface RestConverter <R , E > {

    default E mapToEntity(final R rest) {
        throw new UnsupportedOperationException();
    }

    default R mapToRest(final E entity) {
        throw new UnsupportedOperationException();
    }
}