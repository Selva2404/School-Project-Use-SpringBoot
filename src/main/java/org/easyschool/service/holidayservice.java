package org.easyschool.service;

import lombok.extern.slf4j.Slf4j;
import org.easyschool.Model.holiday;
import org.easyschool.Repository.holidayRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class holidayservice {

    @Autowired
    holidayRepo holidayRepo;

    public List<holiday> collectHolidaydata() {

        return holidayRepo.findAll();
    }
}
