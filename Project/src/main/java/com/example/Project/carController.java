package com.example.Project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.List;
@Controller
@RequestMapping("/")
public class carController {
    private final carService carservice;

    @Autowired
    public carController(carService carservice) {
        this.carservice = carservice;
    }

//    @Controller
//    public static class AuthInterceptor implements HandlerInterceptor {
//        @Override
//        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//            HttpSession session = request.getSession();
//            String username = (String) session.getAttribute("username");
//            if (username == null) {
//                response.sendRedirect("/?error=login");
//                return false;
//            }
//            return true;
//        }
//    }

    @GetMapping("/")
    public String showLoginPage() {
        return "login";
    }

//    @PostMapping("/login")
//    public String login(@RequestParam("username") String username, HttpSession session) {
//        // Save the username in session
//        session.setAttribute("username", username);
//        // Redirect user to /books endpoint
//        return "redirect:/books";
//    }
//
//    @GetMapping("/logout")
//    public String logout(HttpSession session) {
//        // Save the username in session
//        session.removeAttribute("username");
//        // Redirect user to /books endpoint
//        return "redirect:/books";
//    }

    @GetMapping("/cars/addform")
    public String addForm(Model model) {
        return "carInsert";
    }

    @PostMapping("/cars/add")
    public String addCar(@RequestParam String carName, @RequestParam String carDesc, @RequestParam int price, @RequestParam String carModel, @RequestParam String carImage, Model model) {
        Car newCar = new Car(0, carName, carDesc, price,carImage, carModel);
        carservice.addBook(newCar);
        return "redirect:/cars";
    }

    @GetMapping("/cars/{id}")
    public String getBookDetails(@PathVariable("id") Long id, Model model) {
        Car cars = carservice.getCarById(id);
        model.addAttribute("cars", cars);
        return "carDetails";
    }

    @DeleteMapping("/cars/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        carservice.deleteCarById(id);
        return "redirect:/cars";
    }
    @GetMapping("/cars")
    public String homePage(Model model) {
        var cars = this.carservice.listCars();
        model.addAttribute("cars", cars);
        return "carList";
    }
    @GetMapping("/cars/updateform/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Car cars = carservice.getCarById(id);
        model.addAttribute("cars", cars);
        return "carUpdate";
    }

    @PutMapping("/cars/update/{id}")
    public String updateCar(@PathVariable("id") Long id, @ModelAttribute("cars") Car car, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            car.setCarId(id);
            return "carUpdate";
        }
        carservice.updateCarById(id, car);
        return "redirect:/cars";
    }

}
