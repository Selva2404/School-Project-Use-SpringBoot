package org.easyschool.service;

import jakarta.servlet.http.HttpSession;
import org.easyschool.Consents.EasySchoolConsent;
import org.easyschool.Model.Address;
import org.easyschool.Model.Profile;
import org.easyschool.Model.person;
import org.easyschool.Repository.personRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProfileService {

    @Autowired
    private personRepository personRepository;


    public boolean updateuserdetails(Profile profile, HttpSession session) {

        person person=(person)session.getAttribute("defaultDisplay");

        person.setName(profile.getName());
        person.setMailid(profile.getMailid());
        person.setMobileNum(profile.getMobileNum());
        if(person.getAddress() == null && !(person.getAddress().getAddressid()>0)) {
            person.setAddress(new Address());
        }

        person.getAddress().setAddress1(profile.getAddress1());
        person.getAddress().setAddress1(profile.getAddress2());
        person.getAddress().setCity(profile.getCity());
        person.getAddress().setState(profile.getState());
        person.getAddress().setZipcode(profile.getZipcode());
        personRepository.save(person);

        session.setAttribute("defaultDisplay",person);
        return true;
    }
}
