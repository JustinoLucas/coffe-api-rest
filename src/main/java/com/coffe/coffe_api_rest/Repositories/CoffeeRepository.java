package com.coffe.coffe_api_rest.Repositories;

import com.coffe.coffe_api_rest.Model.CoffeeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeRepository extends JpaRepository<CoffeeModel, Integer> {
}
