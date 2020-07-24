package com.springbootcamp.springsecurity;

import com.springbootcamp.springsecurity.embeddableclass.AddressCopy;
import com.springbootcamp.springsecurity.entity.*;
import com.springbootcamp.springsecurity.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@SpringBootTest
class SpringSecurityApplicationTests {

	@Autowired
	UserRepository userRepository;

	@Autowired
	SellerRepository sellerRepository;

	@Autowired
	BuyerRepository buyerRepository;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	ProductReviewRepository productReviewRepository;

	@Autowired
	AddressRepository addressRepository;

	@Autowired
	OrdersRepository orderClassRepository;

	@Autowired
	ProductVariationRepository productVariationRepository;

	@Autowired
	OrderStatusRepository orderStatusRepository;

	@Autowired
	OrderProductRepository orderProductRepository;

	@Autowired
	CategoryMetadataFieldRepository categoryMetadataFieldRepository;

	@Autowired
	CategoryMetadataFieldValueRepository categoryMetadataFieldValueRepository;
	@Test
	void contextLoads() {
	}

	/*@Test
	public void addImage() throws IOException {
		Buyer buyer=buyerRepository.findByEmail("seema.tandon55@gmail.com");
		buyer.saveImage("/home/akshita/Desktop/user1.png");
		buyerRepository.save(buyer);
	}

	@Test
	public void testReadImage()
	{
		User user=userRepository.findByEmail("seema.tandon55@gmail.com");
		File file=new File("/home/akshita/Desktop/pictures/" +user.getUsername() +".png");
		FileOutputStream fileOutputStream=null;
		try
		{
			fileOutputStream=new FileOutputStream(file);
			fileOutputStream.write(user.getProfileImage());

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				fileOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}*/

/*	@Test
	public void createAddress()
	{

		User user=userRepository.findByUsername("Nehab19");
		HashSet<Address> addressList=new HashSet<Address>();
		Address address1=new Address();
		address1.setCity("New delhi");
		address1.setCountry("India");
		address1.setAddress("West-Patel Nagar");
		address1.setLabel("Shadipur");
		address1.setState("Delhi");
		address1.setZip_code(110008);
		addressList.add(address1);
		address1.setUser(user);



		Address address2=new Address();
		address2.setCity("New delhi");
		address2.setCountry("India");
		address2.setAddress("East-Patel Nagar");
		address2.setLabel("Patel Nagar");
		address2.setState("Delhi");
		address2.setZip_code(110008);
		addressList.add(address2);
		address2.setUser(user);
		user.setAddress(addressList);
		userRepository.save(user);


	}

	@Test
	public void addCategoryOne()
	{
		Category category=new Category();
		category.setName("Books");
		categoryRepository.save(category);

	}

	@Test
	public void addCategoryTwo()

	{

		Category category=new Category();
		category.setName("Mobiles");
		categoryRepository.save(category);

	}
	@Test
	public void addSubCategoryOne()
	{
		Category parentCategory=categoryRepository.findByName("Books");
		Category childCategory=new Category();
		childCategory.setName("Love Stories");
		childCategory.setCategory(parentCategory);

		categoryRepository.save(childCategory);
	}

	@Test
	public void addSubCategoryTwo()
	{
		Category categoryList=categoryRepository.findByName("Books");
		Category category=new Category();
		category.setName("Fiction Stories");
		category.setCategory(categoryList);
		categoryRepository.save(category);
	}

	@Test
	public void addSubCategoryThree()
	{
		Category categoryList=categoryRepository.findByName("Mobiles");
		Category category=new Category();
		category.setName("Samsung");
		category.setCategory(categoryList);
		categoryRepository.save(category);
	}

	@Test
	public void addSubCategoryFour()
	{
		Category categoryList=categoryRepository.findByName("Mobiles");
		Category category=new Category();
		category.setName("Iphone");
		category.setCategory(categoryList);
		categoryRepository.save(category);
	}


	@Test
	public void addProductOne()
	{

		Category category=categoryRepository.findByName("Love Stories");
		Product product=new Product();
		product.setName("I too had a love story!");
		product.setBrand("Penguin");
		product.setDescription("A love story by Ravinder Singh");
		product.setIs_cancellable(true);
		product.setIs_returnable(true);
		product.setIs_active(true);
		product.setCategory(category);
		category.setCategory(category);
		productRepository.save(product);

	}

	@Test
	public void addProductTwo()
	{

		Category category=categoryRepository.findByName("Iphone");
		Product product=new Product();
		product.setName("Iphone 7");
		product.setBrand("Apple");
		product.setDescription("A mobile phone with advanced features");
		product.setIs_cancellable(true);
		product.setIs_returnable(true);
		product.setIs_active(true);
		product.setCategory(category);
		category.setCategory(category);
		productRepository.save(product);

	}
	@Test
	public void SellerProducts()
	{

		Seller seller=sellerRepository.findById(1l).get();
		System.out.println(seller);
		Product product=productRepository.findByName("Iphone 7");
		System.out.println(product);
		Product product1=productRepository.findByName("I too had a love story!");
		System.out.println(product1);
		Set<Product> productSet=new HashSet<>();
		productSet.add(product);
		productSet.add(product1);
		seller.setProducts(productSet);
		sellerRepository.save(seller);


	}
	@Test
	public void addReview()
	{
		ProductReview review=new ProductReview();
		review.setRating(4);
		review.setReview("Its okay..");
		review.setProduct_review_id(1l);
		Product product=productRepository.findByName("I too had a love story!");
		Buyer buyer=buyerRepository.findById(3l).get();
		Set<ProductReview> productReviewSet=new HashSet<>();

		productReviewSet.add(review);
		product.setProductReviews(productReviewSet);
		buyer.setProductReviews(productReviewSet);
		review.setBuyer(buyer);
		review.setProduct(product);
		productReviewRepository.save(review);


	}
	@Test
	public void addReviewTwo()
	{
		ProductReview review=new ProductReview();
		review.setRating(4);
		review.setReview("Its okay..");
		//review.setProductReviewId(1l);
		Product product=productRepository.findByName("Iphone 7");
		Buyer buyer=buyerRepository.findById(3l).get();
		Set<ProductReview> productReviewSet=new HashSet<>();

		productReviewSet.add(review);
		product.setProductReviews(productReviewSet);
		buyer.setProductReviews(productReviewSet);
		review.setBuyer(buyer);
		review.setProduct(product);
		productReviewRepository.save(review);


	}

	@Test
	public void createVariationOne()
	{

		Product product=productRepository.findByName("Iphone 7");
		Set<ProductVariation> productVariationSet=new HashSet<>();
		ProductVariation productVariation=new ProductVariation();
		productVariation.setQuantity_available(5);
		//productVariation.setMetadata("{" + "\"description\": \"BLACK COLOR BRUSH\"," + "}");
		productVariation.setPrice(15000f);
		productVariation.setPrimary_image_name("Black color image");
		productVariationSet.add(productVariation);
		productVariation.setProduct(product);
		product.setProductVariation(productVariationSet);
		productRepository.save(product);
	}



	@Test
	public void createVariationTwo()
	{

		Product product=productRepository.findByName("I too had a love story!");
		Set<ProductVariation> productVariationSet=new HashSet<>();
		ProductVariation productVariation=new ProductVariation();
		productVariation.setQuantity_available(7);
		//productVariation.setMetadata("{" + "\"description\": \"BLACK COLOR BRUSH\"," + "}");
		productVariation.setPrice(150f);
		productVariation.setPrimary_image_name("A love story");
		productVariationSet.add(productVariation);
		productVariation.setProduct(product);
		product.setProductVariation(productVariationSet);
		productRepository.save(product);
	}

	@Test
	public void addOrders()
	{
		Set<Orders> orderClassSet=new HashSet<>();
		Orders orderClass=new Orders();
		Address address=addressRepository.findById(9l).get();
		orderClass.setAddressCopy(new AddressCopy(address));
		orderClass.setDate_created(new Date());
		orderClass.setAmount_paid(1234f);
		orderClass.setPayment_method("Cash");
		orderClassSet.add(orderClass);
		Buyer buyer=buyerRepository.findById(3l).get();
		buyer.setOrders(orderClassSet);
		orderClass.setBuyer(buyer);
		buyerRepository.save(buyer);


	}
	@Test
	public void testOrderStatus() {
		Orders orderClass= orderClassRepository.findById(39l).get();
		ProductVariation productVariation = productVariationRepository.findById(38l).get();
		Set<OrderProduct> orderProductSet = new HashSet<>();
		OrderProduct orderProduct = new OrderProduct();
		orderProduct.setPrice(130f);
		orderProduct.setQuantity(10);
		orderProductSet.add(orderProduct);
		orderClass.setOrderProduct(orderProductSet);
		orderProduct.setProductVariation(productVariation);
		orderProduct.setOrders(orderClass);
		orderProductRepository.save(orderProduct);

	}
	@Test
	public void addStatus()
	{

		OrderProduct orderProduct=orderProductRepository.findById(44l).get();
		System.out.println(orderProduct);
		OrderStatus orderStatus=new OrderStatus();
		orderStatus.setFrom_status(FROM_STATUS.ORDER_CONFIRMED);
		orderStatus.setTo_status(TO_STATUS.DELIVERED);
		orderStatus.setTransition_notes_comment("Your product is delivered");
		orderProduct.setOrderStatus(orderStatus);
		orderStatus.setOrderProduct(orderProduct);
		orderProductRepository.save(orderProduct);


	}

	@Test
	public void addCart()
	{
		Buyer buyer=buyerRepository.findById(3l).get();
		ProductVariation productVariation=productVariationRepository.findById(37l).get();
		ProductVariation productVariation1=productVariationRepository.findById(38l).get();

		Set<ProductVariation> productVariationSet=new HashSet<>();
		productVariationSet.add(productVariation);
		productVariationSet.add(productVariation1);
		Cart cart=new Cart();
		cart.setQuantity(2);
		cart.setIs_wislist_item(true);
		cart.setBuyer(buyer);
		cart.setProductVariations(productVariationSet);
		buyer.setCart(cart);
		buyerRepository.save(buyer);


	}

	@Test

	public void listAllSellers()
	{
		List<Seller> sellerList= (List<Seller>) sellerRepository.findAll();
		sellerList.forEach(seller -> System.out.println(seller.getUsername()));

	}


	@Test
	public void listAllBuyers()
	{
		List<Buyer> buyerList= (List<Buyer>) buyerRepository.findAll();
		buyerList.forEach(buyer -> System.out.println(buyer.getUsername()));

	}
*/


	/*@Test
	public void addCategoryMetadataFieldValue()
	{

		Category category=categoryRepository.findById(380L).orElse(null);

		//System.out.println(category);
		CategoryMetadataField categoryMetadataField=categoryMetadataFieldRepository.findById(386L).get();
		//System.out.println(categoryMetadataField);
		List<String> metadataValues=new ArrayList();
		metadataValues.add("blue");
		metadataValues.add("red");
		metadataValues.add("green");
		System.out.println("list completed!");
		CategoryMetadataFieldValue categoryMetadataFieldValue=new CategoryMetadataFieldValue(category,categoryMetadataField);
		System.out.println("object creation");
		categoryMetadataFieldValue.setMetadataValues(metadataValues);
		categoryMetadataFieldValueRepository.save(categoryMetadataFieldValue);

		System.out.println("saving");
	}*/
}