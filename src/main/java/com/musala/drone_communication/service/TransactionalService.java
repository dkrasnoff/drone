package com.musala.drone_communication.service;

import org.springframework.transaction.annotation.Transactional;

/**
 * This is marker interface.
 * NOTE: all implementations of this service must contain only transactional public methods.
 */
@Transactional
public interface TransactionalService {
}
