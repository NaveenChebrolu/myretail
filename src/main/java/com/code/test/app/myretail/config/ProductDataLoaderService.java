package com.code.test.app.myretail.config;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.test.app.myretail.entity.CurrentPriceEntity;
import com.code.test.app.myretail.entity.ProductEntity;
import com.code.test.app.myretail.exception.MyRetailException;
import com.code.test.app.myretail.repository.ProductRepository;

@Service
public class ProductDataLoaderService {

	@Autowired
	private ProductRepository repository;

	@PostConstruct
	public void init() throws MyRetailException {
		if (repository != null) {
			List<ProductEntity> productsList = new ArrayList<ProductEntity>();
			ProductEntity prod1 = new ProductEntity("13860428", new CurrentPriceEntity(13.49, "USD"));
			productsList.add(prod1);
			prod1 = new ProductEntity("16483589", new CurrentPriceEntity(22.99, "USD"));
			productsList.add(prod1);
			prod1 = new ProductEntity("16696652", new CurrentPriceEntity(12.99, "USD"));
			productsList.add(prod1);
			prod1 = new ProductEntity("16752456", new CurrentPriceEntity(18.99, "USD"));
			productsList.add(prod1);
			prod1 = new ProductEntity("15643793", new CurrentPriceEntity(21.59, "USD"));
			productsList.add(prod1);
			repository.deleteAll();
			repository.saveAll(productsList);
		}
	}
}
