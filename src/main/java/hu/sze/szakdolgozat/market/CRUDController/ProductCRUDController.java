package hu.sze.szakdolgozat.market.CRUDController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.zip.Deflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity.BodyBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import hu.sze.szakdolgozat.market.dao.OrderDetailRepository;
import hu.sze.szakdolgozat.market.dao.ProductRepository;
import hu.sze.szakdolgozat.market.dto.ProductResponse;
import hu.sze.szakdolgozat.market.entity.Product;
import hu.sze.szakdolgozat.market.service.OrderService;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("api/admin")
public class ProductCRUDController {
    
    @Autowired
    private ProductRepository productRepository;
	
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderDetailRepository orderDetailRepository;

    @GetMapping("/items")
    public List<ProductResponse> productList(){

        List<ProductResponse> productList = new ArrayList<>();
		List<Product> tempProduct = productRepository.findAll();
		for (int i = 0; i < tempProduct.size(); i++) {
			Product singleProduct = tempProduct.get(i);
			ProductResponse singleProductResponse = new ProductResponse();
			singleProductResponse.setId(singleProduct.getId());
			singleProductResponse.setProductname(singleProduct.getProductname());
			singleProductResponse.setCategory(singleProduct.getCategory());
			singleProductResponse.setDetails(singleProduct.getDetails());
			singleProductResponse.setImage(singleProduct.getImage());
			singleProductResponse.setUnit(singleProduct.getUnit());
			singleProductResponse.setPrice(singleProduct.getPrice());
			singleProductResponse.setProducer(singleProduct.getUser().getId());
			 
			productList.add(singleProductResponse);

		}
		return productList;
    }


    @GetMapping("/item/{id}")
	public ProductResponse item(@PathVariable Integer id) {

		ProductResponse productResponse = new ProductResponse();
		Optional<Product> tempProduct = productRepository.findById(id);
		Product singleProduct = tempProduct.get();

		productResponse.setId(singleProduct.getId());
        productResponse.setProductname(singleProduct.getProductname());
        productResponse.setCategory(singleProduct.getCategory());
        productResponse.setDetails(singleProduct.getDetails());
        productResponse.setImage(singleProduct.getImage());
        productResponse.setUnit(singleProduct.getUnit());
        productResponse.setPrice(singleProduct.getPrice());
        productResponse.setProducer(singleProduct.getUser().getId());

		return productResponse;

	}

	@GetMapping("/myitems/{id}")
	public List<ProductResponse> myProductList(@PathVariable Integer id){

        List<ProductResponse> productList = new ArrayList<>();
		List<Product> tempProduct = productRepository.findAll();
		for (int i = 0; i < tempProduct.size(); i++) {
			
			Product singleProduct = tempProduct.get(i);

			if(singleProduct.getUser().getId().equals(id)){
				ProductResponse singleProductResponse = new ProductResponse();
				singleProductResponse.setId(singleProduct.getId());
				singleProductResponse.setProductname(singleProduct.getProductname());
				singleProductResponse.setCategory(singleProduct.getCategory());
				singleProductResponse.setDetails(singleProduct.getDetails());
				singleProductResponse.setImage(singleProduct.getImage());
				singleProductResponse.setUnit(singleProduct.getUnit());
				singleProductResponse.setPrice(singleProduct.getPrice());
				singleProductResponse.setProducer(singleProduct.getUser().getId());
				productList.add(singleProductResponse);
			}

		}
		return productList;
    }


	@DeleteMapping("/deleteProduct/{id}")
	public String delete(@PathVariable Integer id) {

		
        productRepository.deleteById(id);
		orderService.refreshAll();
        return "deleted";
	}

	@PostMapping("/addProduct")
	public String uplaodImage(@RequestBody Product product){

		Product tempProduct = new Product();
		tempProduct.setCategory(product.getCategory());
		tempProduct.setDetails(product.getDetails());
		tempProduct.setProductname(product.getProductname());
		tempProduct.setPrice(product.getPrice());
		tempProduct.setUser(product.getUser());
		tempProduct.setUnit(product.getUnit());
		tempProduct.setImage(product.getImage());

		productRepository.save(tempProduct);

		return "ok";
	}

	// public static byte[] compressBytes(byte[] data) {
	// 	Deflater deflater = new Deflater();
	// 	deflater.setInput(data);
	// 	deflater.finish();
	// 	ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
	// 	byte[] buffer = new byte[1024];
	// 	while (!deflater.finished()) {
	// 		int count = deflater.deflate(buffer);
	// 		outputStream.write(buffer, 0, count);
	// 	}
	// 	try {
	// 		outputStream.close();
	// 	} catch (IOException e) {
	// 	}
	// 	System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
	// 	return outputStream.toByteArray();
	// }
}
