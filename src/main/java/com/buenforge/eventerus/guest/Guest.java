package com.buenforge.eventerus.guest;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@NoArgsConstructor
@Entity
@Data
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    private String mobileNumber;

    private String fbAccount;

    private String instagramAccount;

    @Column(name = "seat_count", nullable = false)
    private int seatCount = 1;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime rsvpDateTime;

    public enum Status {
        INVITE_SENT,
        RSVP_YES,
        RSVP_NO
    }
}
