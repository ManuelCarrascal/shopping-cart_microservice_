package emazon.cart.ports.persistence.mysql.adapter;

import emazon.cart.domain.model.Pagination;
import emazon.cart.domain.model.Product;
import emazon.cart.domain.model.dto.ProductDetailsCart;
import emazon.cart.domain.model.dto.ProductListCartDomain;
import emazon.cart.domain.spi.IStockConnectionPersistencePort;
import emazon.cart.infrastructure.config.feign.IStockFeignClient;
import emazon.cart.ports.persistence.mysql.util.StockConnectionAdapterConstants;
import feign.FeignException;

import java.util.Collections;

import lombok.RequiredArgsConstructor;

import java.util.List;


@RequiredArgsConstructor
public class StockConnectionAdapter implements IStockConnectionPersistencePort {

    private final IStockFeignClient stockFeignClient;

    public boolean existById(Long id) {
        try {
            return stockFeignClient.existById(id);
        } catch (FeignException.NotFound e) {
            return false;
        }
    }

    public boolean isStockSufficient(Long productId, Integer quantity) {
        try {
            return stockFeignClient.isStockSufficient(productId, quantity);
        } catch (FeignException.NotFound e) {
            return false;
        }
    }

    public List<String> getCategoryNamesByProductId(Long productId) {
        try {
            return stockFeignClient.getCategoryNamesByProductId(productId);
        } catch (FeignException.NotFound e) {
            return Collections.emptyList();
        }
    }

    @Override
    public Pagination<ProductDetailsCart> getAllProductsPaginatedByIds(int page, int size, boolean isAscending, String categoryName, String brandName, ProductListCartDomain productListCartDomain) {
        return stockFeignClient.getProductsCart(page, size, isAscending, categoryName, brandName, productListCartDomain);
    }

    @Override
    public Double getProductPriceById(Long productId) {
        try {
            return stockFeignClient.getProductPriceById(productId);
        } catch (FeignException.NotFound e) {
            return StockConnectionAdapterConstants.DEFAULT_PRODUCT_PRICE;
        }
    }

    @Override
    public List<Product> getAllProducts(List<Long> productIds) {
        try {
            return stockFeignClient.getAllProducts();
        } catch (FeignException.NotFound e) {
            return Collections.emptyList();
        }
    }

}
