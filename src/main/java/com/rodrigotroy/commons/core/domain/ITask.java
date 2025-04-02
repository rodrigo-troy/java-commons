package com.rodrigotroy.commons.core.domain;

import com.rodrigotroy.commons.core.model.response.IResponse;

public interface ITask {
    IResponse execute(Object o);
}
