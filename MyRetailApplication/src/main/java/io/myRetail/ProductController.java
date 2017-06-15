package io.myRetail;


import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
public class ProductController {
	
	@Autowired
	private PriceRepository priceRepository;

	@Autowired
	private RestTemplate restTemplate = new RestTemplate();
	
	
	//GET service: retrieving the appropriate product data, retrieving null if product not found
	@RequestMapping(value = "/products/{id}", method = RequestMethod.GET, produces = "application/json")
	public String retrieveProductById(@PathVariable("id") long id) throws IOException, JSONException{
		
		String url="http://redsky.target.com/v2/pdp/tcin/"+id+"?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics";
		String str=null;
		try {
			str= restTemplate.getForObject(url,String.class, id);
		} catch (HttpClientErrorException ex)   {
	    if (ex.getStatusCode() != HttpStatus.NOT_FOUND) {
	        throw ex;
	    }
	    return "{\"id\":" + id + ",\"name\":" + null + ",\"current_price\":\"Product not found\"}";
	
	}
	
		JSONObject root = new JSONObject(str);
		JSONObject product = root.getJSONObject("product");
		JSONObject item = product.getJSONObject("item");
		if(item==null){
			return "product not found"; 
		}
		JSONObject product_description = item.getJSONObject("product_description");
		String name=product_description.getString("title");
		return "{\"id\":" + id + ",\"name\":\"" + name + "\"," + priceRepository.findById(id) + "}";
	
	}	
	
	//POST Service: updating the price value of the product with the passed id
	@RequestMapping(value = "/products/{id}", method = RequestMethod.PUT, produces = "application/json")
	public String editPrice(@PathVariable("id") long id, @RequestBody String toUpdate ) throws JSONException{
		
		JSONObject root = new JSONObject(toUpdate);
		JSONObject updatedPrice = root.getJSONObject("current_price");
		String name=root.getString("name");
		double cost=updatedPrice.getDouble("value");
		String currency=updatedPrice.getString("currency_code");
		priceRepository.save(new Price(id, cost,currency));
		return "{\"id\":" + id + ",\"name\":\"" + name + "\"," + priceRepository.findById(id) + "}";
	
	}
	
}