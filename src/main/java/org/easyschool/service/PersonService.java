package org.easyschool.service;

import jakarta.validation.Valid;
import org.easyschool.Model.person;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    public boolean setPerson(@Valid person person) {
        return false;
    }
}
