package com.kitm.library.backend.domain.order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 17.04.22
 */
public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {
}
