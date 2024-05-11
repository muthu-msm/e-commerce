package org.mkt.manager.service;

public interface IBaseService<T, L> {

    L create(T request);

    L update(T request);

    Long delete(Long id);

    L get(Long id);
}
