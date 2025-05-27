package org.easyschool.service;


import org.easyschool.Consents.EasySchoolConsent;
import org.easyschool.Model.Roles;
import org.easyschool.Model.person;
import org.easyschool.Repository.personRepository;
import org.easyschool.Repository.roleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private personRepository personRepository;
    @Autowired
    private roleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean setPerson(person person) {

        Roles role= roleRepository.getByRoleName(EasySchoolConsent.STUDENT_ROLE);
        person.setRole(role);
        person.setPwd(passwordEncoder.encode(person.getPwd()));
        person = personRepository.save(person);

        if (person != null && person.getPerson_id() >0) {
            return true;
        }
        return false;


    }
}
