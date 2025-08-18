package com.rsh.mapper;

import com.rsh.ui.dto.ContactDTO;
import com.rsh.model.ContactFX;

public class ContactMapper {

    public static ContactDTO toDTO(ContactFX fx) {
        return new ContactDTO(
            null,  // ID sera généré côté backend
            fx.getFirstName(),
            fx.getLastName(),
            fx.getEmail(),
            fx.getPhone(),
            fx.getUserId()
        );
    }

    public static ContactFX toFX(ContactDTO dto) {
        return new ContactFX(
            dto.getId(),
            dto.getFirstName(),
            dto.getLastName(),
            dto.getEmail(),
            dto.getPhone(),
            dto.getUserId()
        );
    }
}

