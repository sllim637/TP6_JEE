package com.djamware.springmvc.controllers;

import com.djamware.springmvc.models.Product;
import com.djamware.springmvc.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {
    @Autowired
    ProductRepository productrepository;
    @RequestMapping("/product")
    public String product(Model model){
        model.addAttribute("product",productrepository.findAll());
        return "product";
    }
    @RequestMapping("/create")
    public String create(Model model){
        return"create";
    }
    @RequestMapping("/save")
    public String save(@RequestParam String prodName,@RequestParam String prodDesc,@RequestParam Double prodPrice, @RequestParam String prodImage){
        Product product =new Product();
        product.setProdName(prodName);
        product.setProdDesc(prodDesc);
        product.setProdPrice(prodPrice);
        product.setProdImage(prodImage);
        productrepository.save(product);
        return "redirect:/show/"+ product.getId();
    }
    @RequestMapping("/show/{id}")
    public String delete(@PathVariable Long id, Model model){
        model.addAttribute("product",productrepository.findById(id).orElse(null));
        return "show";
    }
    @RequestMapping("/delete")
    public String delete (@RequestParam Long id){
        Product product =productrepository.findById(id).orElse(null);
        productrepository.delete(product);
        return "redirect:/product";
    }
    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Long id , Model model){
        model.addAttribute("product",productrepository.findById(id).orElse(null));
        return"edit";
    }
    @RequestMapping("update")
    public String update(@RequestParam Long id,@RequestParam String prodName,@RequestParam String prodDesc,@RequestParam Double prodPrice, @RequestParam String prodImage){
        Product product =productrepository.findById(id).orElse(null);
        product.setProdName(prodName);
        product.setProdDesc(prodDesc);
        product.setProdPrice(prodPrice);
        product.setProdImage(prodImage);
        productrepository.save(product);

        return "redirect:/show/" + product.getId();
    }
}
