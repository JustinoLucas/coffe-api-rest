package com.coffe.coffe_api_rest.Controllers;

import com.coffe.coffe_api_rest.Model.CoffeeModel;

import com.coffe.coffe_api_rest.Repositories.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;


import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/coffee")
@CrossOrigin(origins = "http://localhost:5173")
public class CoffeeController {

    private final Path fileImageLocation = Paths.get("uploads").toAbsolutePath().normalize();

    @Autowired
    private CoffeeRepository coffeeRepository;

    public CoffeeController() throws IOException {
        Files.createDirectories(fileImageLocation);
        System.out.println("Pasta de upload criada em: " + fileImageLocation.toString());
    }

    @GetMapping
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<List<CoffeeModel>> listAll(){
        List<CoffeeModel>    coffees = coffeeRepository.findAll();
        return ResponseEntity.ok(coffees);
    }

    public CoffeeController(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }


    @PostMapping
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<String> addCoffee(
            @RequestParam("nome_coffee") String nome,
            @RequestParam("preco_coffee") BigDecimal preco,
            @RequestParam("desc_coffee") String descricao,
            @RequestParam("image_coffee") MultipartFile image) {

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(image.getOriginalFilename()));

        String uniqueFileName = System.currentTimeMillis() + "_" + fileName;

        if (image.isEmpty()) {
            return ResponseEntity.badRequest().body("Arquivo vazio");
        }
        try {
            Path targetLocation = fileImageLocation.resolve(uniqueFileName);
            image.transferTo(targetLocation);



            CoffeeModel coffee = new CoffeeModel();
            coffee.setNome_coffee(nome);
            coffee.setPreco_coffee(preco);
            coffee.setDesc_coffee(descricao);
            coffee.setImage_coffee(uniqueFileName);

            coffeeRepository.save(coffee);

            System.out.println("Salvando imagem em: " + targetLocation.toString());
            return ResponseEntity.ok("Café adicionado com sucesso!");

        } catch (IOException ex) {
            return ResponseEntity.badRequest().body("Erro ao salvar o arquivo");
        }
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/image/{fileName:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable String fileName) {
        try {
            // Diretório onde as imagens estão armazenadas
            Path filePath = Paths.get("uploads").resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                String contentType = Files.probeContentType(filePath); // Detecta o tipo MIME
                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType != null ? contentType : "application/octet-stream"))
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.badRequest().build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }




    @DeleteMapping("/{id_coffee}")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id_coffee) {
        System.out.println("Recebida requisição para excluir o café com ID: " + id_coffee);

        Optional<CoffeeModel> coffee = coffeeRepository.findById(id_coffee);

        if (coffee.isPresent()) {
            // Excluir a imagem associada
            String imageFileName = coffee.get().getImage_coffee();
            try {
                Path imagePath = Paths.get("uploads").resolve(imageFileName).normalize();
                Files.deleteIfExists(imagePath); // Exclui a imagem, se existir
                System.out.println("Imagem excluída com sucesso: " + imageFileName);
            } catch (IOException e) {
                System.out.println("Erro ao excluir a imagem: " + e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }

            // Excluir o café do banco de dados
            coffeeRepository.deleteById(id_coffee);
            System.out.println("Café excluído com sucesso!");
            return ResponseEntity.noContent().build();
        }

        System.out.println("Café com ID " + id_coffee + " não encontrado.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


}
