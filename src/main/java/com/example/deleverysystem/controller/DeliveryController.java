package com.example.deleverysystem.controller;

import com.example.deleverysystem.entity.DeliveryPersonnel;
import com.example.deleverysystem.service.DeliveryPersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/delivery")
@CrossOrigin("*")
public class DeliveryController {
    @Autowired
    private DeliveryPersonnelService deliveryPersonnelService;

    @PostMapping("/create")
    public DeliveryPersonnel createDeliveryPersonnel(@RequestBody DeliveryPersonnel deliveryPersonnel) {
        return deliveryPersonnelService.createDeliveryPersonnel(deliveryPersonnel);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(Integer id) {
        deliveryPersonnelService.deleteById(id);
    }

    @GetMapping("/view/{id}")
    public DeliveryPersonnel findById(Integer id) {
        return deliveryPersonnelService.findById(id);
    }

    @PostMapping("/save")
    public Iterable<DeliveryPersonnel> viewAllDeliveryPersonnel() {
        return deliveryPersonnelService.findAll();
    }
}
