package com.rodrigotroy.commons.core.domain;

import java.io.Serializable;

public record User(Object id, Object session, String name, String password) implements Serializable {
}


