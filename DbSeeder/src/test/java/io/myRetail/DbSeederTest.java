package io.myRetail;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class DbSeederTest {
	
	@Mock
	private PriceRepository priceRepository;
	
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetPriceById(){
		Price price = new Price(123456,1000.51,"USD");
		when(priceRepository.findById(123456)).thenReturn(price);
		Price result = priceRepository.findById(123456);
		assertEquals(123456, result.getId());
		//assertEquals(1000.51, result.getValue());
		assertEquals("USD", result.getCode());
	}
	
	@Test
	public void saveToDo(){
		Price price = new Price(8,10000,"USD");
		when(priceRepository.save(price)).thenReturn(price);
		Price result = priceRepository.save(price);
		assertEquals(8, result.getId());
		//assertEquals(10000, result.getValue());
		assertEquals("USD", result.getCode());
	}
}
	