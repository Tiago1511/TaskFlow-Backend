package com.event.TaskFlow.shared;

import java.io.Serializable;

public interface RestConverter <T extends Serializable, P extends Serializable> {

    default T mapToEntity (final P data) {
        throw new UnsupportedOperationException();
    }

    default P mapToRest (final T entity) {
        throw new UnsupportedOperationException();
    }
}
