package com.coffe.coffe_api_rest.DTOs;

import java.math.BigDecimal;


public record CoffeeDTO(Integer id_coffee, String nome_coffee, BigDecimal preco_coffee, String desc_coffee, String image_coffee) {
}
