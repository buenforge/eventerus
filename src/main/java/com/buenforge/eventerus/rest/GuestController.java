package com.buenforge.eventerus.rest;

import com.buenforge.eventerus.guest.Guest;
import com.buenforge.eventerus.guest.GuestService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/guests")
public class GuestController {

    private final GuestService guestService;

    @Autowired
    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @Operation(summary = "Get all guests", description = "Returns a list of all guests")
    @GetMapping
    public List<Guest> getAllGuests() {
        return guestService.findAll();
    }

    @Operation(summary = "Get guest by ID", description = "Returns a guest by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the guest"),
            @ApiResponse(responseCode = "404", description = "Guest not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Guest> getGuestById(@PathVariable Long id) {
        return guestService.findById(id)
                .map(guest -> new ResponseEntity<>(guest, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Create a new guest", description = "Adds a new guest to the system")
    @PostMapping
    public ResponseEntity<Guest> createGuest(@Valid @RequestBody Guest guest) {
        Guest savedGuest = guestService.save(guest);
        return new ResponseEntity<>(savedGuest, HttpStatus.CREATED);
    }

    @Operation(summary = "Update an existing guest", description = "Updates an existing guest by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated the guest"),
            @ApiResponse(responseCode = "404", description = "Guest not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Guest> updateGuest(@PathVariable Long id, @Valid @RequestBody Guest guest) {
        return guestService.findById(id)
                .map(existingGuest -> {
                    guest.setId(existingGuest.getId());
                    Guest updatedGuest = guestService.save(guest);
                    return new ResponseEntity<>(updatedGuest, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Delete a guest", description = "Deletes a guest by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deleted the guest"),
            @ApiResponse(responseCode = "404", description = "Guest not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGuest(@PathVariable Long id) {
        if (guestService.findById(id).isPresent()) {
            guestService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}