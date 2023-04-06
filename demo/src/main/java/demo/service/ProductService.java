package demo.service;

import demo.dto.Product;
import demo.entity.UserInfo;
import demo.repository.UserInfoRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ProductService {
    @Autowired
   private UserInfoRepository UserRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    List<Product> ProductList;

    // addUser
    public UserInfo addUser(UserInfo userInfo){
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
         UserInfo userInfo1=UserRepo.save(userInfo);


        return  userInfo1;
    }

    // Create 100 Product
    @PostConstruct
    public void loadProductFromDB(){

        ProductList = IntStream.rangeClosed(1, 100).
                mapToObj(i -> Product.builder()
                .productId(i)
                .name("product" + i)
                .qty(new Random().nextInt(10))
                .price(new Random().nextInt(5000)).build()

        ).collect(Collectors.toList());


    }

    //Get All Product
    public List<Product>getAllProduct(){

//        ProductList.stream().forEach(each ->
//                {
//                    System.out.println(each.getProductId());
//                    System.out.println(each.getName());
//                    System.out.println(each.getQty());
//                    System.out.println(each.getPrice());
//                }
//
//        );
        return ProductList;

    }

    //getProductById
    public Product getProductById(int pid){
        Product p1=ProductList.stream().filter(product -> product.getProductId()==pid).findAny().orElseThrow(() ->new RuntimeException("Product"+pid+"not Found"));
        return p1;
    }


}
