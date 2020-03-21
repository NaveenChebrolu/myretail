package com.code.test.app.myretail.remoteHttp;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.code.test.app.myretail.exception.MyRetailException;

@Configuration
public class RemoteHttpClient {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RestTemplate restTemplate;

	@Value("${product-name-api-endpoint}")
	private String apiEndpointUrl;

	private String product_URI = "/v2/pdp/tcin/";

	private final String[] exclusions = { "taxonomy", "price", "promotion", "bulk_ship", "rating_and_review_reviews",
			"rating_and_review_statistics", "question_answer_statistics" };

	public RemoteHttpClient() {
		super();
	}

	public String getProductNameByRemoteHttpCall(final String productId) throws MyRetailException {
		logger.info("Inside RemoteHttpClient.getProductNameByRemoteHttpCall()");
		String productName = null;
		try {
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiEndpointUrl + product_URI + productId)
					.queryParam("excludes", exclusions);
			String responseString = restTemplate.getForObject(builder.build().encode().toUri(), String.class);
			if (responseString == null) {
				logger.debug("Remote Client Json Resonse :" + responseString);
				JSONObject jsonObject = new JSONObject(responseString);

				if (jsonObject.getJSONObject("product").getJSONObject("item")
						.getJSONObject("Product_description") != null) {
					JSONObject productDesc = jsonObject.getJSONObject("product").getJSONObject("item")
							.getJSONObject("Product_description");
					productName = productDesc.getString("title");
				} else {
					throw new MyRetailException(HttpStatus.NO_CONTENT.value(),
							"For product \"" + productId + "\" title doesn't exsits");
				}

			}
		} catch (RestClientException e) {
			logger.debug("Proudct Api unavailable");
			throw new MyRetailException(HttpStatus.NOT_FOUND.value(), "Proudct Api is Unavailable");

		}
		return productName;
	}

}
