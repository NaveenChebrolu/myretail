package com.code.test.app.myretail.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.code.test.app.myretail.entity.CurrentPriceEntity;
import com.code.test.app.myretail.entity.ProductEntity;
import com.code.test.app.myretail.exception.MyRetailException;
import com.code.test.app.myretail.remoteHttp.RemoteHttpClient;
import com.code.test.app.myretail.repository.ProductRepository;

@RunWith(SpringRunner.class)
@TestPropertySource(properties = { "product-name-api-endpoint = http://redsky.target.com" })
public class ProductServiceTest {

	@Mock
	private ProductService toTest;

	@MockBean
	private ProductRepository respository;

	@MockBean
	private RemoteHttpClient httpClient;

	@Value("${product-name-api-endpoint}")
	private String endpoint;

	@Configuration
	public static class Config {

		@Bean
		public static PropertySourcesPlaceholderConfigurer propertiesResolver() {
			return new PropertySourcesPlaceholderConfigurer();
		}

	}

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getProductByIdTest() throws MyRetailException {
		ProductEntity productEntity = new ProductEntity("13860428", new CurrentPriceEntity(13.49, "USD"));
		doReturn(Optional.of(productEntity)).when(respository).findById(anyString());
		assertEquals("13860428", productEntity.getId());
	}

}
