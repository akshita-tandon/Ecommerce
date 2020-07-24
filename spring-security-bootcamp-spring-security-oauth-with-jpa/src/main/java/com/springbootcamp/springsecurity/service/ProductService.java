package com.springbootcamp.springsecurity.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbootcamp.springsecurity.dto.*;
import com.springbootcamp.springsecurity.embeddableclass.AddressCopy;
import com.springbootcamp.springsecurity.entity.*;
import com.springbootcamp.springsecurity.exception.*;
import com.springbootcamp.springsecurity.repository.*;
import org.json.simple.JSONArray;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Type;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    BuyerRepository buyerRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailService emailService;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryMetadataFieldValueRepository categoryMetadataFieldValueRepository;

    @Autowired
    ProductVariationRepository productVariationRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ImageService imageService;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    OrderStatusRepository orderStatusRepository;

    @Autowired
    OrderProductRepository orderProductRepository;

    public boolean addProduct(Principal principal, ProductRequestDTO productRequestDTO) {

        String username = principal.getName();
        Seller seller = sellerRepository.findByUsername(username);
        if (seller == null) {
            throw new UserNotFoundException("There is no seller found. ");
        } else {
            Category category = categoryRepository.findById(productRequestDTO.getCategoryId()).orElse(null);
            Product productByName = productRepository.findByNameAndSellerAndBrandAndCategory(productRequestDTO.getName(), seller, productRequestDTO.getBrand(), category);
            if (productByName == null) {

                List<Long> categories = categoryRepository.fetchLeafCategoryId();
                if (categories.contains(productRequestDTO.getCategoryId())) {
                    ModelMapper modelMapper = new ModelMapper();
                    Product product = modelMapper.map(productRequestDTO, Product.class);
                    product.setSeller(seller);
                    productRepository.save(product);
                    User user = userRepository.findByUsername("Admin");
                    emailService.sendEmail(user.getEmail(), "Activation of Product",
                            "This is my new Product :" + productRequestDTO.getName() + "\n" +
                                    "Brand :" + productRequestDTO.getBrand() + "\nDescription: " + productRequestDTO.getDescription() + "\nCategory: " + productRequestDTO.getCategoryId() + " \n Please activate this product.");
                    return true;
                } else
                    throw new ChildCategoryNotFoundException("This is not a Leaf category");

            } else
                throw new ProductAlreadyExistsException("the product already exists with seller");
        }
    }

    public ProductResponseDTO viewProduct(Principal principal, Long productId) {

        String username = principal.getName();
        Seller seller = sellerRepository.findByUsername(username);
        Product product = productRepository.findById(productId).orElse(null);
        if (product == null) {
            throw new ProductNotFoundException("There is no product found in the database");
        } else if (product.getSeller().getId().equals(seller.getId()) && product.getDeleted()==false) {
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
            ProductResponseDTO productResponseDTO = modelMapper.map(product, ProductResponseDTO.class);
            return productResponseDTO;
        } else
            throw new ProductNotFoundException("Seller is not associated with this product");

    }

    public PageImpl<ProductPagingResponseDto> viewAllProduct(Principal principal, Pageable pageable) {
        String username = principal.getName();
        Seller seller = sellerRepository.findByUsername(username);
        pageable = PageRequest.of(0, 10, Sort.Direction.ASC, "productId");
        Page<Product> productPage = productRepository.findAllBySellerAndIsDeleted(seller,false, pageable);
        List<ProductPagingResponseDto> productPagingResponseDtos = productPage.stream().map(this::toSellerAllProductsDTO).collect(Collectors.toList());
        return new PageImpl<ProductPagingResponseDto>(productPagingResponseDtos);

    }

    private ProductPagingResponseDto toSellerAllProductsDTO(Product product) {
        return new ProductPagingResponseDto(product.getName(), product.getProductId(), product.getCategory(), product.getDescription(), product.getBrand(), product.getSeller(), product.getCategory(),product.getCancellable(),product.getReturnable());
    }

    public boolean deleteProduct(Principal principal, Long productId) {
        String username = principal.getName();
        Seller seller = sellerRepository.findByUsername(username);
        Product product = productRepository.findByProductIdAndSeller(productId, seller);
        if (product == null) {
            throw new ProductNotFoundException("There is no product associated with this seller");
        } else {
            product.setDeleted(true);
            productRepository.save(product);
            return true;
        }
    }

    public boolean updateProduct(Principal principal, ProductUpdateRequestDTO productUpdateRequestDTO) {
        String username = principal.getName();
        Seller seller = sellerRepository.findByUsername(username);
        Product product = productRepository.findByProductIdAndSeller(productUpdateRequestDTO.getProductId(), seller);
        if (product == null) {
            throw new ProductNotFoundException("There is no product associated with this seller");
        } else {
            if (productUpdateRequestDTO.getName() != null) {
                Product productByName = productRepository.findByNameAndSellerAndBrandAndCategory(productUpdateRequestDTO.getName(), seller, product.getBrand(), product.getCategory());
                if (productByName == null) {
                    product.setName(productUpdateRequestDTO.getName());
                    productRepository.save(product);
                    return true;
                } else
                    throw new ProductAlreadyExistsException("The product already exists with this name");
            }
            if (productUpdateRequestDTO.getDescription() != null) {
                product.setDescription(productUpdateRequestDTO.getDescription());
                productRepository.save(product);
                return true;
            }
            if (productUpdateRequestDTO.getIsCancellable() != null) {
                product.setCancellable(productUpdateRequestDTO.getIsCancellable());
                productRepository.save(product);
                return true;
            }
            if (productUpdateRequestDTO.getIsReturnable() != null) {
                product.setReturnable(productUpdateRequestDTO.getIsReturnable());
                productRepository.save(product);
                return true;
            }
        }
        return false;
    }

    public boolean activateProduct(Long productId) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product == null) {
            throw new ProductNotFoundException("The Product is not Found");
        } else {
            product.setActive(true);
            productRepository.save(product);
            emailService.sendEmail(product.getSeller().getEmail(), "Product Activated", "Your product listed for activation is now activated");
            return true;
        }
    }

    public boolean deactivateProduct(Long productId) {

        Product product = productRepository.findById(productId).orElse(null);
        if (product == null) {
            throw new ProductNotFoundException("The Product is not Found");
        } else {
            product.setActive(false);
            productRepository.save(product);
            emailService.sendEmail(product.getSeller().getEmail(), "Product De-Activated", "Your product is now de-activated");
            return true;
        }

    }

    public Boolean addProductVariation(MultipartFile image, Principal principal, ProductVariationRequestDTO productVariationRequestDTO) throws MetadataFieldValueNotFoundException, IOException {

        String uploadedImage=imageService.uploadImageProduct(image);
        String username = principal.getName();
        Seller seller = sellerRepository.findByUsername(username);
        Product product = productRepository.findById(productVariationRequestDTO.getProductId()).orElse(null);
        Long categoryId = productVariationRequestDTO.getCategoryId();
        if (categoryId.equals(product.getCategory().getCategoryId())) {
            if (product.getSeller().equals(seller)) {

                JSONArray metadataValues = productVariationRequestDTO.getMetadataValues();
                for (Object object : metadataValues) {

                    ObjectMapper objectMapper = new ObjectMapper();
                    Map<String, Object> map = (Map<String,Object>)objectMapper.convertValue(object, Map.class);
                    Object fieldId = map.get("metadata_field_id");
                    List<String> metadata = (List<String>) map.get("metadatavalues");
                    String values = categoryMetadataFieldValueRepository.fetchMetadataValues(categoryId, fieldId);
                    List<String> valuesList = Arrays.asList(values.split(","));
                    for (String str : metadata) {
                        if (!valuesList.contains(str)) {
                            throw new MetadataFieldValueNotFoundException("There are no such values containing in metadata field value");
                        }
                    }
                }

                productVariationRequestDTO.setMetadata(metadataValues.toString());
                productVariationRequestDTO.setPrimaryImageName(uploadedImage);
                modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
                ProductVariation productVariation = modelMapper.map(productVariationRequestDTO, ProductVariation.class);
                product.insertProductVariation(productVariation);
                productVariationRepository.save(productVariation);
                return true;

            } else
                throw new ProductNotFoundException("There is no product associated with this seller");

        } else
            throw new CategoryNotFoundException("There is no product associated with this category");
    }


    public ProductVariationResponseDTO viewProductVariation(Principal principal, Long productVariationId) throws ProductVariationNotFoundException {
        String username = principal.getName();
        Seller seller = sellerRepository.findByUsername(username);
        ProductVariation productVariation = productVariationRepository.findById(productVariationId).orElse(null);
        Long id = productVariation.getProduct().getSeller().getId();
        if (id.equals(seller.getId()) && !productVariation.getProduct().getDeleted()) {
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
            ProductVariationResponseDTO productVariationResponseDTO = modelMapper.map(productVariation, ProductVariationResponseDTO.class);
            return productVariationResponseDTO;
        } else {
            throw new ProductVariationNotFoundException("There is no product variation ");
        }
    }

    public Page<AllProductVariationResponseDTO> viewAllProductVariations(Principal principal, Long productId, Pageable pageable) {

        String username = principal.getName();
        Seller seller = sellerRepository.findByUsername(username);
        Product product = productRepository.findById(productId).orElse(null);
        List<ProductVariation> productVariation = productVariationRepository.findByProduct(product, pageable);
        if (product.getSeller().getId().equals(seller.getId()) && product.getDeleted()==false) {
            List<AllProductVariationResponseDTO> allProductVariationResponseDTOS = productVariation.stream().map(this::toSellerAllProductsResponseDTO).collect(Collectors.toList());
            return new PageImpl<AllProductVariationResponseDTO>(allProductVariationResponseDTOS);
        }
        return null;
    }

    private AllProductVariationResponseDTO toSellerAllProductsResponseDTO(ProductVariation productVariation) {
        return new AllProductVariationResponseDTO(productVariation.getProductVariationId(), productVariation.getMetadata(), productVariation.getPrice(), productVariation.getQuantityAvailable(), productVariation.getProduct().getProductId(), productVariation.getPrimaryImageName());
    }

    public Boolean updateProductVariation(Principal principal, UpdateVariationDTO updateVariationDTO) throws IOException, MetadataFieldValueNotFoundException {

        String username = principal.getName();
        Seller seller = sellerRepository.findByUsername(username);
        ProductVariation productVariation = productVariationRepository.findById(updateVariationDTO.getProductVariationId()).orElse(null);
        if (productVariation.getProduct().getSeller().getId().equals(seller.getId())) {
            if (productVariation.getProduct().getActive() && !productVariation.getProduct().getDeleted()) {
                if (updateVariationDTO.getIsActive() != null) {
                    productVariation.setIsActive(updateVariationDTO.getIsActive());
                    productVariationRepository.save(productVariation);
                    return true;
                }
                if (updateVariationDTO.getQuantityAvailable() != null) {
                    productVariation.setQuantityAvailable(updateVariationDTO.getQuantityAvailable());
                    productVariationRepository.save(productVariation);
                    return true;
                }
                if (updateVariationDTO.getPrice() != null) {
                    productVariation.setPrice(updateVariationDTO.getPrice());
                    productVariationRepository.save(productVariation);
                    return true;
                }
                if (updateVariationDTO.getMetadataArray() != null) {

                    Long categoryId = productVariation.getProduct().getCategory().getCategoryId();
                    JSONArray array = updateVariationDTO.getMetadataArray();
                    for (Object object : array) {
                        ObjectMapper objectMapper = new ObjectMapper();
                        Map<String, Object> map = objectMapper.convertValue(object, Map.class);
                        Object fieldId = map.get("metadata_field_id");
                        List<String> metadata = (List<String>) map.get("metadatavalues");
                        String values = categoryMetadataFieldValueRepository.fetchMetadataValues(categoryId, fieldId);
                        List<String> valuesList = Arrays.asList(values.split(","));
                        for (String str : metadata) {
                            if (!valuesList.contains(str)) {
                                throw new MetadataFieldValueNotFoundException("There are no values similar to metadata field value for given category id and field id");
                            }
                        }
                    }
                    productVariation.setMetadata(array.toString());
                    productVariationRepository.save(productVariation);
                    return true;
                }
            }else
                throw new ProductNotFoundException("There is no product associated");
        }else
            throw new ProductNotFoundException("There is no product associated iwth this repository");

        return null;
    }

    public BuyerProductResponseDTO viewProductBuyer(Long productId) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product == null)
            throw new ProductNotFoundException("There is no product with the given id ");
        else {
            if (!product.getDeleted()){
                if(  product.getActive()){
                   if(!product.getProductVariation().isEmpty()){
                       modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
                       BuyerProductResponseDTO buyerProductResponseDTOS = modelMapper.map(product, BuyerProductResponseDTO.class);
                       return buyerProductResponseDTOS;
                   }else{
                       throw new ProductNotFoundException("Product Variation is  empty.");
                   }
                } else{
                    throw new ProductNotFoundException("Product is not active.");
                }
            }else{
                throw new ProductNotFoundException("Product is deleted.");
            }


            }

    }


    private BuyerProductResponseDTO toAdminAllProductsResponseDTO(Product product) {
        return new BuyerProductResponseDTO(product.getProductId(), product.getName(), product.getDescription(), product.getActive(), product.getCancellable(), product.getReturnable(), product.getBrand(), product.getCategory(), product.getProductVariation());
    }

    public Page<BuyerProductResponseDTO> viewAllProductBuyer(Long categoryId) {
        Category category = (Category) categoryRepository.findById(categoryId).orElse(null);
        if (category == null)
            throw new CategoryNotFoundException("There is no category with the given id ");
        else {
            List<Long> longList = categoryRepository.fetchLeafCategoryId();
            if (longList.contains(categoryId)) {
                List<Product> product = productRepository.findByCategoryAndIsDeleted(category,false);
                List<BuyerProductResponseDTO> adminProductResponseDTOS = product.stream().map(this::toAdminAllProductsResponseDTO).collect(Collectors.toList());
                return new PageImpl<BuyerProductResponseDTO>(adminProductResponseDTOS);

            } else
                throw new ChildCategoryNotFoundException("This is not a Leaf Category");
        }
    }

    public Page<BuyerProductResponseDTO> viewSimilarProductsBuyer(Long productId, Pageable pageable) {
        pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "productId");
        Product product = productRepository.findById(productId).orElse(null);
        if (product == null) {
            throw new ProductNotFoundException("There is no product with this id");
        } else {
            if(product.getActive() && !product.getDeleted()){
                List<Product> products = productRepository.fetchSimilarProducts(product.getCategory().getCategoryId(), pageable);
                List<BuyerProductResponseDTO> buyerProductResponseDTOS = products.stream().map(this::toAdminAllProductsResponseDTO).collect(Collectors.toList());
                return new PageImpl<BuyerProductResponseDTO>(buyerProductResponseDTOS);
            }

        }

        return null;
    }

    public Page<BuyerProductResponseDTO> viewAllProductAdmin(Pageable pageable) {
        pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "productId");
        Page<Product> products = productRepository.findAll(pageable);
        if (products == null)
            throw new ProductNotFoundException("There are no products ");
        else {
            List<BuyerProductResponseDTO> adminProductResponseDTOS = products.stream().map(this::toAdminAllProductsResponseDTO).collect(Collectors.toList());
            return new PageImpl<BuyerProductResponseDTO>(adminProductResponseDTOS);
        }
    }

    public BuyerProductResponseDTO viewProductAdmin(Long productId) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product == null)
            throw new ProductNotFoundException("There is no product with the given id ");
        else {
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            BuyerProductResponseDTO buyerProductResponseDTOS = modelMapper.map(product, BuyerProductResponseDTO.class);
            return buyerProductResponseDTOS;
        }
    }

    public boolean addToCart(Principal principal, AddToCartRequestDTO addToCartRequestDTO) {

        String username=principal.getName();
        Buyer buyer=buyerRepository.findByUsername(username);
        Long productVariationId=addToCartRequestDTO.getProductVariationId();
        ProductVariation productVariation= productVariationRepository.findById(productVariationId).orElse(null);
        Cart alreadyCart=cartRepository.findByBuyerAndProductVariations(buyer,productVariation);
        if(alreadyCart!=null){
            if(alreadyCart.getDeleted()){
                alreadyCart.setDeleted(false);
                cartRepository.save(alreadyCart);
                return true;
            }
        }
       else{
            if (buyer!= null){

                Cart cart=new Cart(buyer,productVariation);
                cart.setQuantity(addToCartRequestDTO.getQuantity());
                cart.setBuyer(buyer);
                cart.setProductVariations(productVariation);
                cartRepository.save(cart);
                return true;
            }else
                return false;
        }

        return false;
    }

    //from single variation page
    public boolean addToWishList(Principal principal,Long productVariationId) {

        String username=principal.getName();
        Buyer buyer=buyerRepository.findByUsername(username);
        ProductVariation productVariation= productVariationRepository.findById(productVariationId).orElse(null);
        Cart alreadyCart=cartRepository.findByBuyerAndProductVariations(buyer,productVariation);
        if(alreadyCart!=null) {
            if (alreadyCart.getDeleted()) {
                alreadyCart.setDeleted(false);
                cartRepository.save(alreadyCart);
                return true;
            }
        }else{
            if (buyer!= null){
                Cart cart=new Cart(buyer,productVariation);
                cart.setQuantity(1);
                cart.setBuyer(buyer);
                cart.setWishListItem(true);
                cart.setProductVariations(productVariation);
                cartRepository.save(cart);
                return true;
            }else
                return false;
        }

        return false;
    }

    public boolean moveToCart(Principal principal, Long productVariationId) {
        String username=principal.getName();
        Buyer buyer=buyerRepository.findByUsername(username);
        ProductVariation productVariation=productVariationRepository.findById(productVariationId).orElse(null);
        Cart cart=cartRepository.findByBuyerAndProductVariations(buyer,productVariation);
        if(cart!=null){
            cart.setWishListItem(false);
            cartRepository.save(cart);
            return true;
        }else
            return false;

    }



    public boolean moveToWishList(Long buyerId, Long productVariationId) {
        Buyer buyer=buyerRepository.findById(buyerId).orElse(null);
        ProductVariation productVariation=productVariationRepository.findById(productVariationId).orElse(null);
        Cart cart=cartRepository.findByBuyerAndProductVariations(buyer,productVariation);
        if(cart!=null){
            cart.setWishListItem(true);
            cartRepository.save(cart);
            return true;
        }else
            return false;

    }

    //both from wishlist and cart
    public boolean deleteItem(Long buyerId, Long productVariationId) {
        Buyer buyer=buyerRepository.findById(buyerId).orElse(null);
        ProductVariation productVariation=productVariationRepository.findById(productVariationId).orElse(null);
        Cart cart=cartRepository.findByBuyerAndProductVariations(buyer,productVariation);
        if(cart!=null){
            cart.setDeleted(true);
        cartRepository.save(cart);
        return true;
        }else
            return false;

    }

    public List<CartResponseDTO> getItemsFromCart(Principal principal) {
        String username=principal.getName();
        Buyer buyer=buyerRepository.findByUsername(username);
        List<Cart> allByBuyer = cartRepository.findAllByBuyerAndIsWishListItem(buyer,false);
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Type typeList = new TypeToken<List<CartResponseDTO>>() {
        }.getType();
        List<CartResponseDTO> cartList = modelMapper.map(allByBuyer, typeList);
        return cartList;
    }

    public List<CartResponseDTO> getItemsFromWishList(Principal principal) {
        String username=principal.getName();
        Buyer buyer=buyerRepository.findByUsername(username);
        List<Cart> allByBuyer = cartRepository.findAllByBuyerAndIsWishListItem(buyer,true);
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Type typeList = new TypeToken<List<CartResponseDTO>>() {
        }.getType();
        List<CartResponseDTO> cartList = modelMapper.map(allByBuyer, typeList);
        return cartList;
    }

    public boolean updateQuantityFromCart(Long buyerId, Long productVariationId, Integer quantity) {
        Buyer buyer=buyerRepository.findById(buyerId).orElse(null);
        ProductVariation productVariation=productVariationRepository.findById(productVariationId).orElse(null);
        Cart cart=cartRepository.findByBuyerAndProductVariations(buyer,productVariation);
        if(cart!=null){
            cart.setQuantity(quantity);
            cartRepository.save(cart);
            return true;
        }else
            return false;
    }

    public boolean orderNowForSingleProduct(Principal principal, Long productVariationId,Integer quantity,Long addressId) {
        String username=principal.getName();
        Buyer buyer=buyerRepository.findByUsername(username);
        ProductVariation productVariation=productVariationRepository.findById(productVariationId).orElse(null);
        if(productVariation!=null){
            Orders orders=new Orders();
            orders.setBuyer(buyer);
            orders.setAmountPaid(productVariation.getPrice()*quantity);
            orders.setDateCreated(new Date());
            orders.setPaymentMethod("Cash-On-Delivery");
            Address address=addressRepository.findById(addressId).orElse(null);
            if(address!=null){
                AddressCopy addressCopy=new AddressCopy(address);
                orders.setAddressCopy(addressCopy);
            }
            ordersRepository.save(orders);

            Set<OrderProduct> orderProductSet = new HashSet<>();
            OrderProduct orderProduct=new OrderProduct();
            orderProduct.setPrice(productVariation.getPrice());
            orderProduct.setQuantity(quantity);
            orderProduct.setProductVariation(productVariation);
            orderProduct.setProduct_variation_metadata(productVariation.getMetadata());
            orderProduct.setOrders(orders);
            orderProductSet.add(orderProduct);
            orders.setOrderProduct(orderProductSet);
            ordersRepository.save(orders);
            OrderStatus orderStatus=new OrderStatus();
            orderStatus.setOrderProduct(orderProduct);
            orderStatus.setFromStatus(FROM_STATUS.ORDER_PLACED);
            orderStatusRepository.save(orderStatus);
            productVariation.setQuantityAvailable(productVariation.getQuantityAvailable()-1);
            productVariationRepository.save(productVariation);
            return true;
        }
        return false;
    }

    public boolean orderNowForCart(Principal principal,Long addressId) {
        String username=principal.getName();
        Buyer buyer=buyerRepository.findByUsername(username);
        List<Cart> listCart=cartRepository.findAllByBuyerAndIsDeletedAndIsWishListItem(buyer,false,false);
        System.out.println(listCart);
        Orders orders=new Orders();
        orders.setBuyer(buyer);
        Address address=addressRepository.findById(addressId).orElse(null);
        if(address!=null){
            AddressCopy addressCopy=new AddressCopy(address);
            orders.setAddressCopy(addressCopy);
        }
        orders.setDateCreated(new Date());
        orders.setPaymentMethod("Cash-On-Delivery");

        ordersRepository.save(orders);
        Set<OrderProduct> orderProductSet = new HashSet<>();
        for(Cart cart:listCart){
            ProductVariation productVariation=productVariationRepository.findById(cart.getProductVariations().getProductVariationId()).orElse(null);
             OrderProduct orderProduct=new OrderProduct();
            orderProduct.setPrice(productVariation.getPrice());
            orderProduct.setQuantity(cart.getQuantity());
            orderProduct.setProductVariation(productVariation);
            orderProduct.setProduct_variation_metadata(productVariation.getMetadata());
            orderProduct.setOrders(orders);

            orderProductSet.add(orderProduct);
            orderProductRepository.save(orderProduct);
            Float totalAmount=orders.getAmountPaid();
            totalAmount+=productVariation.getPrice()*(cart.getQuantity());
            orders.setAmountPaid(totalAmount);
            orders.setOrderProduct(orderProductSet);
            ordersRepository.save(orders);
            OrderStatus orderStatus=new OrderStatus();
            orderStatus.setOrderProduct(orderProduct);
            orderStatus.setFromStatus(FROM_STATUS.ORDER_PLACED);
            orderStatusRepository.save(orderStatus);
            productVariation.setQuantityAvailable(productVariation.getQuantityAvailable()-1);
            productVariationRepository.save(productVariation);
            Cart cart1=cartRepository.findByBuyerAndProductVariations(buyer,productVariation);
            cart1.setDeleted(true);
            cartRepository.save(cart1);

        }

         return true;
        }


    public boolean removeOrder(Long orderProductId) {
        OrderProduct orderProduct=orderProductRepository.findById(orderProductId).orElse(null);
        OrderStatus orderStatus=orderStatusRepository.findById(orderProductId).orElse(null);
        if(orderProduct!=null){
            orderProduct.setIsDeleted(true);
            Float amount=orderProduct.getPrice()*orderProduct.getQuantity();
            Orders orders=ordersRepository.findByOrderProduct(orderProduct);
            orders.setAmountPaid(orders.getAmountPaid()-amount);
            ordersRepository.save(orders);
            orderProductRepository.save(orderProduct);
            if(orderStatus!=null){
                if(orderStatus.getFromStatus()==FROM_STATUS.ORDER_PLACED){
                    orderStatus.setToStatus(TO_STATUS.CANCELLED);
                }
                if(orderStatus.getFromStatus()==FROM_STATUS.ORDER_CONFIRMED){
                    orderStatus.setToStatus(TO_STATUS.CANCELLED_ORDER_CONFIRMED);
                }
            }
            return true;
        }else
         return false;
    }

    public List<OrderProductSellerResponseDTO> viewOrdersBySellers(Principal principal) {

        String username=principal.getName();
        Seller seller=sellerRepository.findByUsername(username);
        List<OrderProduct> orderProductList=new ArrayList<OrderProduct>();
        if(seller!=null){
            Iterable<OrderProduct> allproducts = orderProductRepository.findAllByIsDeleted(false);
            for (OrderProduct orderProduct:allproducts) {
                if(orderProduct.getProductVariation().getProduct().getSeller()==seller){
                   orderProductList.add(orderProduct);
                }
            }
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
            Type typeList = new TypeToken<List<OrderProductSellerResponseDTO>>() {
            }.getType();
            List<OrderProductSellerResponseDTO> orderProductListSeller = modelMapper.map(orderProductList, typeList);
            return orderProductListSeller;
        }else
        return null;
    }

    public boolean confirmOrRejectOrder(Long orderStatusId, String change) {
        OrderStatus orderStatus=orderStatusRepository.findById(orderStatusId).orElse(null);
        if(orderStatus!=null){
            if(change.equals("c")){
                orderStatus.setToStatus(TO_STATUS.ORDER_CONFIRMED);
                orderStatus.setFromStatus(FROM_STATUS.ORDER_CONFIRMED);
            } if(change.equals("r"))
            {
                orderStatus.setToStatus(TO_STATUS.ORDER_REJECTED);
                orderStatus.setFromStatus(FROM_STATUS.ORDER_REJECTED);
            }
            orderStatusRepository.save(orderStatus);
            return true;
        }else
            return false;
    }

    public List<OrderBuyerResponseDTO> viewOrdersByBuyers(Principal principal) {

        String username=principal.getName();
        Buyer buyer=buyerRepository.findByUsername(username);
        Iterable<Orders> orders=ordersRepository.findAllByBuyer(buyer);
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Type typeList = new TypeToken<List<OrderBuyerResponseDTO>>() {
        }.getType();
        List<OrderBuyerResponseDTO> orderProductListSeller = modelMapper.map(orders, typeList);
        return orderProductListSeller;

    }
}

