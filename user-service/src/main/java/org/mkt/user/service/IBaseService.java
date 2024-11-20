package org.mkt.user.service;

import org.mkt.common.dto.Response;

public interface IBaseService<T, L> {

    L create(T request);

    L update(T request);

    Response<Long> delete(Long id);

    L get(Long id);
}
