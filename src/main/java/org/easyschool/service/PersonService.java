package org.easyschool.service;


import org.easyschool.Consents.EasySchoolConsent;
import org.easyschool.Model.person;
import org.easyschool.Repository.personRepository;
import org.easyschool.Repository.roleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private personRepository personRepository;
    @Autowired
    private roleRepository roleRepository;

    public boolean setPerson(person person) {

        person.setRole(roleRepository.getReferenceByRoleName(EasySchoolConsent.STUDENT_ROLE));
        person = personRepository.save(person);
        if (person != null && person.getPerson_id() >0) {
            return true;
        }
        return false;


    }
}
