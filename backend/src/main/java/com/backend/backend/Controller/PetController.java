package com.backend.backend.Controller;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Optional;

import com.backend.backend.Model.Pet;
import com.backend.backend.Service.PetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("api/v1/pets")
public class PetController {

    @Autowired
    private PetService petService;

    @GetMapping
    public Collection<Pet> getPets() {
        return petService.getPets();
    }

    @PostMapping
    public Pet postPet(@RequestBody Pet pet) {
        return petService.createPet(pet);
    }
    @GetMapping(value = "/{id}")
    public Optional<Pet> getPetById(@PathVariable("id") BigInteger id) {
        return petService.getPetById(id);
    }
}
