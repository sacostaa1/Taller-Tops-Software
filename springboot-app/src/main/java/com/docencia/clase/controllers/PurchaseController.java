package com.docencia.clase.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.docencia.clase.models.Car;
import com.docencia.clase.models.Purchase;
import com.docencia.clase.repositories.PurchaseRepository;

@Controller
public class PurchaseController {

    @Autowired
    private PurchaseRepository purchaseRepository;

    private final List<Car> cars = List.of(
            new Car(1L, "Mercedes-Benz S-Class", 120000.0),
            new Car(2L, "BMW 7 Series", 110000.0),
            new Car(3L, "Audi A8", 100000.0),
            new Car(4L, "Lamborghini Urus", 220000.0),
            new Car(5L, "Rolls-Royce Phantom", 450000.0)
    );

    @GetMapping("/")
    public String showIndex() {
        return "activities/index";
    }

    @GetMapping("/purchase/new")
    public String showCreateForm(Model model) {
        model.addAttribute("cars", cars);
        return "activities/act2";
    }

    @PostMapping("/purchase/create")
    public String createPurchase(@RequestParam("typeCar") String typeCar,
                                @RequestParam("someField") String someField,
                                Model model) {
        Car selectedCar = cars.stream().filter(c -> c.getModel().equals(typeCar)).findFirst().orElse(null);
        if (selectedCar != null) {
            Purchase purchase = new Purchase(null, selectedCar.getId(), System.currentTimeMillis(), someField, typeCar, selectedCar.getPrice());
            purchaseRepository.save(purchase);
            model.addAttribute("successMessage", "Compra realizada con éxito! Redirigiendo...");
        }
        return "activities/act2";  
    }

    @GetMapping("/purchase/list")
    public String listPurchases(Model model) {
        List<Purchase> purchases = purchaseRepository.findAll();
        model.addAttribute("purchases", purchases);
        return "activities/act4";
    }

    @DeleteMapping("/purchase/delete/{id}")
    @ResponseBody
    public String deletePurchase(@PathVariable Long id) {
        Integer intId = Math.toIntExact(id); // Convierte Long a Integer
        if (purchaseRepository.existsById(intId)) {
            purchaseRepository.deleteById(intId);
            return "Compra eliminada correctamente";
        } else {
            return "Error: La compra no existe";
        }
    }
}