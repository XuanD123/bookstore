package backend.bookstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import backend.bookstore.domain.Category;
import backend.bookstore.domain.CategoryRepository;

@Controller
public class CategoryController {

    private final CategoryRepository categoryRepo;

    public CategoryController(CategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    // --- Näytä kaikki kategoriat ---
    @GetMapping("/categorylist")
    public String categoryList(Model model) {
        model.addAttribute("categories", categoryRepo.findAll());
        return "categorylist";  // Thymeleaf-template
    }

    // --- Näytä lomake uuden kategorian lisäämiseksi ---
    @GetMapping("/addcategory")
    public String addCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "addcategory"; // lomake-template
    }

    // --- Tallenna uusi kategoria ---
    @PostMapping("/savecategory")
    public String saveCategory(Category category) {
        categoryRepo.save(category);
        return "redirect:/categorylist";
    }
}
