package ua.com.ouw.spring_bootstrap_practice_task.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.ouw.spring_bootstrap_practice_task.dao.ProductDao;
import ua.com.ouw.spring_bootstrap_practice_task.models.Product;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MainController {

    @Autowired
    private ProductDao productDao;

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("allProd", productDao.findAll());
        return "index";
    }

    @PostMapping("/")
    public String tryim(){
        return "saveProduct";
    }

    @GetMapping("/showAll")
    public String showAll(Model model){
        model.addAttribute("products", productDao.findAll());
        return "showAllPr";
    }

    @GetMapping("/showWherePriceHigher1000")
    public String showWhereProductsHigher1000(Model model){
        List<Product> all = productDao.findAll();
        List<Product> collect = all.stream().filter(product -> product.getPrice() > 20).collect(Collectors.toList());
        model.addAttribute("productsWhere", productDao.findAll().stream().filter(product -> product.getPrice() > 40)
                .collect(Collectors.toList()));
        System.out.println(collect);
        return "showWherePriceHigher1000";
    }

    @GetMapping("/showLast10")
    public String showLast10(Model model){
        List<Product> sorted = productDao.findAll(Sort.by(Sort.Order.desc("id")));
        List<Product> collect = sorted.stream().limit(3).collect(Collectors.toList());
        System.out.println(collect);
        model.addAttribute("last10", collect);
        return "showLast10";
    }

@GetMapping("/showWherePriceHigher2000")
    public String showWhereProductsHigher2000(Model model){
        model.addAttribute("productsWhere2000", productDao.findAll().stream().filter(product -> product.getPrice() > 100)
                .collect(Collectors.toList()));
        return "showWherePriceHigher2000";
    }

    @GetMapping("/showByAlphabet")
    public String showByAlphabet(Model model){
        model.addAttribute("products", productDao.findAll(Sort.by("name")));
        return "showByAlphabet";
    }


    @PostMapping("/saveProduct")
    public String saveProduct(@RequestParam String name, Integer price, String describing, Model model){
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setDescribing(describing);
        productDao.save(product);

        System.out.println(product);

        return "saveProduct";
    }


}
