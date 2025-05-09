package org.easyschool.controller;


import lombok.extern.slf4j.Slf4j;
import org.easyschool.Model.holiday;
import org.easyschool.service.holidayservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
public class holidaycontroller {

    @Autowired
    holidayservice holidayservice;

    @GetMapping(value = "/holidays")
    public String displayHolidays(@RequestParam(required = false) boolean festival,
                                  @RequestParam(required = false) boolean federal, Model model) {
        model.addAttribute("festival", festival);
        model.addAttribute("federal", federal);
        List<holiday> holidays = holidayservice.collectHolidaydata();
        log.info("holidays: {}", holidays);
        holiday.Type[] types = holiday.Type.values();
        for (holiday.Type type : types) {
            model.addAttribute(type.toString(),
                    (holidays.stream().filter(holiday -> holiday.getType().equals(type)).collect(Collectors.toList())));
        }
        return "holidays";
    }

}
