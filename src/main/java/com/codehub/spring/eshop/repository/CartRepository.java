package com.codehub.spring.eshop.repository;

import com.codehub.spring.eshop.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Optional;

/**
 * Created by Dimitris on 16/5/2018.
 */

@Repository
@Transactional
public interface CartRepository extends JpaRepository<Cart, Long> {

    public int removeCartByUserIdAndProductId(Long userId, Long productId);

    @Query("update Cart set quantity = :quantity where userId = :userId and productId = :productId")
    public int updateQuantity(@Param("userId") Long userId, @Param("productId") Long productId, @Param("quantity") BigDecimal quantity);

    @Query("update Cart set size = :size where userId = :userId and productId = :productId")
    public int updateSize(@Param("userId") Long userId, @Param("productId") Long productId, @Param("size") String size);

    public int deleteAllByUserId(Long userId);

    public Optional<Cart> findByUserIdAndProductId(Long userId, Long productId);

    public Collection<Cart> findAllByUserId(Long userId);
}
