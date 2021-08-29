package spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spring.entites.Product;
import spring.service.ProductService;

import static com.sun.deploy.services.ServiceManager.service;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Component
@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

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

    @RequestMapping(path = "/product/{id}", method = GET)
    public String showProductByURLId(Model Model, @PathVariable(value = "id") int id) {
       return findProduct(model, id);
    }

//  Находит продукт с заданным идентификатором (как параметр получения) и возвращает представление «результат» или «не найдено»
//  Вызов по форме
    @RequestMapping(path = "/findId", method = GET)
    public String showProductById(Model Model, @RequestParam int id) {
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