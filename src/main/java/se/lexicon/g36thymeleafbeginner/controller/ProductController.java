package se.lexicon.g36thymeleafbeginner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.lexicon.g36thymeleafbeginner.model.Product;

import java.math.BigDecimal;
import java.util.*;

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
        /products/{id}/remove => delete
        /products/{id} => findById
        http://localhost:8080/products/949jfjmf9vdvdf09b8
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
        Product newProduct = new Product(UUID.randomUUID().toString(), name, description, price);
        productList.add(newProduct);
        return "redirect:/products";
    }

    @GetMapping("/products/{id}")
    public String findById(@PathVariable String id, Model model){
        Optional<Product> op = productList.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();

        if(op.isPresent()){
            model.addAttribute("product", op.get());
        }else {
            return "redirect:/products";
        }

        return "productView";
    }

    @GetMapping("/products/{id}/update")
    public String getUpdateForm(@PathVariable String id, Model model){
        Optional<Product> op = productList.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();

        if(op.isPresent()){
            model.addAttribute("product", op.get());
        }else {
            return "redirect:/products";
        }

        return "product-form-update";
    }

    @PostMapping("/products/{id}/update/process")
    public String update(
            @PathVariable String id,
            @RequestParam(name = "name") String name,
            @RequestParam(name = "price") BigDecimal price,
            @RequestParam(name = "description") String description
            ){
        Optional<Product> op = productList.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();

        if(op.isPresent()){
            Product product = op.get();
            product.setName(name.trim());
            product.setPrice(price);
            product.setDescription(description.trim());
            return "redirect:/products/"+id;
        }else {
            return "redirect:/products";
        }
    }

    @GetMapping("/products/{id}/delete")
    public String delete(@PathVariable String id){
        productList.removeIf(product -> product.getId().equals(id));
        return "redirect:/products";
    }


}
