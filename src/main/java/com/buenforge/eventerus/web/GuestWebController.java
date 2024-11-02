package com.buenforge.eventerus.web;

import com.buenforge.eventerus.guest.Guest;
import com.buenforge.eventerus.guest.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/guests")
public class GuestWebController {

    @Autowired
    private GuestService guestService;

    @GetMapping
    public String listGuests(Model model) {
        List<Guest> guests = guestService.findAll();
        model.addAttribute("guests", guests);
        return "guest"; // Return the Thymeleaf template
    }

    @GetMapping("/new")
    public String newGuestForm(Model model) {
        model.addAttribute("guest", new Guest());
        return "guest_form"; // Return the form for adding a new guest
    }

    @GetMapping("/edit/{id}")
    public String editGuestForm(@PathVariable Long id, Model model) {
        Guest guest = guestService.findById(id).orElse(null);
        model.addAttribute("guest", guest);
        return "guest_form";
    }

    @PostMapping("/save")
    public String saveGuest(@ModelAttribute Guest guest) {
        guestService.save(guest);
        return "redirect:/guests";
    }

    @GetMapping("/delete/{id}")
    public String deleteGuest(@PathVariable Long id) {
        guestService.deleteById(id);
        return "redirect:/guests";
    }
}