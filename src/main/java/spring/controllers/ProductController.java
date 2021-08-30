package spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.entites.Product;
import spring.service.ProductService;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Component
@Controller
public class ProductController {
    @Autowired
    private ProductService service;

// Показать все продукты
@RequestMapping("/")
    public String showAll(Model model){
    model.addAttribute("products",service.getAll());
    return "allProducts";
    }

//  Показать форму для новых продуктов
    @RequestMapping("/newProduct")
    public String newProduct(Model model) {
        model.addAttribute("product", new Product());
        return "newProduct";
    }
//  Добавить новый продукт используя объект из формы
    @RequestMapping(path = "/addProduct", method = POST)
    public String addProduct(@ModelAttribute Product product){
    service.addProduct(product);
    return "redirect:/";
    }

// Находит продукт с заданным идентификатором (как часть URL) и возвращает представление «результат» или «не найдено»
    @RequestMapping(path = "/product/{id}", method = GET)
    public String showProductByURLId(Model model, @PathVariable(value = "id") int id) {
       return findProduct(model, id);
    }

//  Находит продукт с заданным идентификатором (как параметр получения) и возвращает представление «результат» или «не найдено»
//  Вызов по форме
    @RequestMapping(path = "/findId", method = GET)
    public String showProductById(Model model, @RequestParam int id) {
        return findProduct(model, id);
    }

   private String findProduct(Model model, int id){
    Product p=service.getById(id);
     if (p!=null){
         model.addAttribute("product", p);
         return "singleProduct";
     }
     model.addAttribute("id", id);
     return "not found";
   }
}