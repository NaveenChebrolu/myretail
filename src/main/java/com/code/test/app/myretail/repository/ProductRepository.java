
package com.code.test.app.myretail.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.code.test.app.myretail.dto.Product;
import com.code.test.app.myretail.entity.ProductEntity;

/**
 * 
 * @author naveen
 *
 */

public interface ProductRepository extends MongoRepository<ProductEntity, String> {

	/**
	 * @param Id
	 * @return
	 */

	public Optional<ProductEntity> findById(String id);

}
