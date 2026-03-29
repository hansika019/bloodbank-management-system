package com.bloodbank.controller;

import com.bloodbank.model.Donor;
import com.bloodbank.repository.DonorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/donors")
public class DonorController {

    @Autowired
    private DonorRepository donorRepository;

    @GetMapping
    public List<Donor> getAllDonors() {
        return donorRepository.findAll();
    }

    @PostMapping
    public Donor addDonor(@RequestBody Donor donor) {
        return donorRepository.save(donor);
    }

    @PutMapping("/{id}")
    public Donor updateDonor(@PathVariable Long id, @RequestBody Donor donorDetails) {
        Donor donor = donorRepository.findById(id).orElseThrow();

        donor.setName(donorDetails.getName());
        donor.setBloodGroup(donorDetails.getBloodGroup());
        donor.setLocation(donorDetails.getLocation());

        return donorRepository.save(donor);
    }

    @DeleteMapping("/{id}")
    public String deleteDonor(@PathVariable Long id) {
        donorRepository.deleteById(id);
        return "Deleted successfully";
    }

    @GetMapping("/search/{bloodGroup}")
    public List<Donor> searchByBloodGroup(@PathVariable String bloodGroup) {
        return donorRepository.findByBloodGroupContainingIgnoreCase(bloodGroup);
    }
}