package demo.controller;

import demo.dto.Product;
import demo.entity.UserInfo;
import demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductControler {
    @Autowired
    private ProductService service;

    @ResponseBody
    @GetMapping("/welcome")
    public String Welcome(){
        return "Welcome to this endpoint is not secure";
    }

    @ResponseBody
    @PostMapping("/userSignup")
    public String addNewUser(@RequestBody UserInfo userInfo){
        System.out.println("Hit signup");
        try {
            UserInfo userInfo1 = this.service.addUser(userInfo);
            return "User Added Successfully";
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            System.out.println(e.fillInStackTrace());
            System.out.println(e.getLocalizedMessage());
            System.out.println(e.getStackTrace());
            System.out.println(e.toString());






            return  ""+e.getCause();
        }
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ResponseBody
    public List<Product> getAllProduct(){
        System.out.println("all end pont hit");
        return service.getAllProduct();
    }
@ResponseBody
    @GetMapping("/{pid}")
    public Product getById(@PathVariable int pid){
    return  this.service.getProductById(pid);
    }

}
