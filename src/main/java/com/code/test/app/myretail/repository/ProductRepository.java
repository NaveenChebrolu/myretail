
package com.code.test.app.myretail.repository;

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

	public ProductEntity findById();

}
