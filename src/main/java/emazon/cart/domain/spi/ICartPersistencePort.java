package emazon.cart.domain.spi;

import emazon.cart.domain.model.Cart;

import java.util.Date;
import java.util.List;

public interface ICartPersistencePort {
    void addProductToCart(Cart cart);

    Cart findProductByUserIdAndProductId(Long userId, Long productId);

    List<Long> findProductIdsByUserId(Long userId);

    void removeProductFromCart(Long userId, Long productId);

    void updateCartItemsUpdatedAt(Long userId, Date updatedAt);
}
