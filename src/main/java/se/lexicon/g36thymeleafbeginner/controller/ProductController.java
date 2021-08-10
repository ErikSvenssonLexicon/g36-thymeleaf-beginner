package se.lexicon.g36thymeleafbeginner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.lexicon.g36thymeleafbeginner.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Controller
public class ProductController {

    private List<Product> productList;

    public ProductController() {
        productList = new ArrayList<>(Arrays.asList(
                new Product(UUID.randomUUID().toString(), "Wooden sword", "Very sharp for being a wooden weapon", BigDecimal.TEN),
                new Product(UUID.randomUUID().toString(), "Iron sword", "Very dull for being a iron weapon", BigDecimal.valueOf(100.90))
        ));
    }

    /*
        /products => all products.
        /products/add => create.
        /products/remove => delete
        products/{id} => findById
     */

    @GetMapping("/products")
    public String products(Model model){
        model.addAttribute("products", productList);
        return "products";
    }

    @GetMapping("/products/add")
    public String getProductForm(){
        return "product-form";
    }

    @PostMapping("/products/process")
    public String processProductForm(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "price") BigDecimal price,
            @RequestParam(name = "description") String description
    ){
        System.out.println(name);
        System.out.println(price);
        System.out.println(description);

        return "redirect:/products";
    }
}
