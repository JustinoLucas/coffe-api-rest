package com.coffe.coffe_api_rest.Model;


import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class CoffeeModel {




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id_coffee;
    public String nome_coffee;
    public BigDecimal preco_coffee;
    public String desc_coffee;
    @Lob
    public byte[] image_coffee;

    public  CoffeeModel(){

    }

    public CoffeeModel(Integer id_coffee, String nome_coffee, BigDecimal preco_coffee, String desc_coffee, byte[] image_coffee) {
        this.id_coffee = id_coffee;
        this.nome_coffee = nome_coffee;
        this.preco_coffee = preco_coffee;
        this.desc_coffee = desc_coffee;
        this.image_coffee = image_coffee;
    }
    public Integer getId_coffee() {
        return id_coffee;
    }

    public void setId_coffee(Integer id_coffee) {
        this.id_coffee = id_coffee;
    }

    public String getNome_coffee() {
        return nome_coffee;
    }

    public void setNome_coffee(String nome_coffee) {
        this.nome_coffee = nome_coffee;
    }

    public BigDecimal getPreco_coffee() {
        return preco_coffee;
    }

    public void setPreco_coffee(BigDecimal preco_coffee) {
        this.preco_coffee = preco_coffee;
    }

    public String getDesc_coffee() {
        return desc_coffee;
    }

    public void setDesc_coffee(String desc_coffee) {
        this.desc_coffee = desc_coffee;
    }

    public byte[] getImage_coffee() {
        return image_coffee;
    }

    public void setImage_coffee(byte[] image_coffee) {
        this.image_coffee = image_coffee;
    }

}
