package com.backend.backend.Controller;

import java.util.Collection;

import com.backend.backend.Entity.Pet;
import com.backend.backend.Service.PetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private PetService petService;

    @GetMapping
    public Collection<Pet> getPets() {
        return petService.getPets();
    }
    @PostMapping
    public Pet postPet(@RequestBody Pet pet){
        return petService.createPet(pet);
    }
}
