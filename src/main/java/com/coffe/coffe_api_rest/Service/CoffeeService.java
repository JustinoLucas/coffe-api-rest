package com.coffe.coffe_api_rest.Service;

import com.coffe.coffe_api_rest.DTOs.CoffeeDTO;
import com.coffe.coffe_api_rest.Model.CoffeeModel;
import com.coffe.coffe_api_rest.Repositories.CoffeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CoffeeService {

    private final CoffeeRepository coffeeRepository;

    public CoffeeService(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    public List<CoffeeDTO> getAllCoffees() {
        return coffeeRepository.findAll()
                .stream()
                .map(coffeeModel -> new CoffeeDTO(coffeeModel.getId_coffee(), coffeeModel.getNome_coffee(),  coffeeModel.getPreco_coffee(), coffeeModel.getDesc_coffee(), coffeeModel.getImage_coffee()))
                .toList();
    }

    public CoffeeDTO createProduto(CoffeeDTO coffeeDTO) {

        CoffeeModel coffeeModel = new CoffeeModel();
        coffeeModel.setNome_coffee(coffeeModel.getNome_coffee());
        coffeeModel.setDesc_coffee(coffeeModel.getDesc_coffee());
        coffeeModel.setPreco_coffee(coffeeModel.getPreco_coffee());
        coffeeModel.setImage_coffee(coffeeModel.getImage_coffee());
        coffeeModel = coffeeRepository.save(coffeeModel);
        return new CoffeeDTO(coffeeModel.getId_coffee(), coffeeModel.getNome_coffee(),  coffeeModel.getPreco_coffee(), coffeeModel.getDesc_coffee(), coffeeModel.getImage_coffee());

    }
}
