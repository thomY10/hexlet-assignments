package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import exercise.model.Contact;
import exercise.repository.ContactRepository;
import exercise.dto.ContactDTO;
import exercise.dto.ContactCreateDTO;

@RestController
@RequestMapping("/contacts")
public class ContactsController {

    @Autowired
    private ContactRepository contactRepository;

    // BEGIN
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ContactDTO create(@RequestBody ContactCreateDTO contactCreateDTO) {

        Contact contact = toEntity(contactCreateDTO);
        contactRepository.save(contact);

        return toDTO(contact);


    }

    private ContactDTO toDTO(Contact contact) {

        ContactDTO result = new ContactDTO();

        result.setFirstName(contact.getFirstName());
        result.setLastName(contact.getLastName());
        result.setPhone(contact.getPhone());
        result.setCreatedAt(contact.getCreatedAt());
        result.setUpdatedAt(contact.getUpdatedAt());
        result.setId(contact.getId());

        return result;

    }

    private Contact toEntity(ContactCreateDTO contactCreateDTO) {

        Contact result = new Contact();

        result.setFirstName(contactCreateDTO.getFirstName());
        result.setLastName(contactCreateDTO.getLastName());
        result.setPhone(contactCreateDTO.getPhone());

        return result;

    }
    // END
}
