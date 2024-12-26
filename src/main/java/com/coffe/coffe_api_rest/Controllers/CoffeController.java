package com.coffe.coffe_api_rest.Controllers;

import com.coffe.coffe_api_rest.DTOs.CoffeeDTO;
import com.coffe.coffe_api_rest.Repositories.CoffeeRepository;
import com.coffe.coffe_api_rest.Service.CoffeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coffee")
public class CoffeController {

    private final CoffeeService coffeeService;

    public CoffeController(CoffeeService coffeeService) {
        this.coffeeService = coffeeService;
    }

    @GetMapping
    public List<CoffeeDTO> getAllCoffees(){
        return coffeeService.getAllCoffees();
    }

    @PostMapping
    public ResponseEntity<CoffeeDTO> createCoffee(@RequestBody CoffeeDTO coffeeDTO){
        CoffeeDTO createdCoffe = coffeeService.createProduto(coffeeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCoffe);
    }
}
