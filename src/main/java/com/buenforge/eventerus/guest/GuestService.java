package com.buenforge.eventerus.guest;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

@Service
public class GuestService {

    private final GuestRepository guestRepository;

    @Autowired
    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    public List<Guest> findAll() {
        return guestRepository.findAll();
    }

    public Optional<Guest> findById(Long id) {
        return guestRepository.findById(id);
    }

    public Guest save(Guest guest) {
        return guestRepository.save(guest);
    }

    public void deleteById(Long id) {
        guestRepository.deleteById(id);
    }
}
