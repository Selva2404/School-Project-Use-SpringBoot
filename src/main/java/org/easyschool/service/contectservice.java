package org.easyschool.service;

import lombok.extern.slf4j.Slf4j;
import org.easyschool.Model.Contect;
import org.easyschool.Repository.contectRpo;
import org.easyschool.controller.EasySchoolConsent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class contectservice {

    @Autowired
    contectRpo contectRpo;


    public boolean getdata(Contect contect){
        boolean flag = false;
        contect.setStatus(EasySchoolConsent.open);
        contect.setCreatedBy(EasySchoolConsent.Anonymous);
        contect.setCreatedAt(LocalDateTime.now());
        Contect s=contectRpo.save(contect);
        if(s != null && s.getContact_id()>0){
            flag=true;
        }
        return flag;
    }
   public List<Contect> getData(){
         return contectRpo.findByStatus(EasySchoolConsent.open);
    }

    public void updateMsgClose(int id, String name){
       log.debug("closeMsg controller id"+id);
        Optional<Contect> contect =contectRpo.findById(id);
        if(contect.isPresent()) {
            Contect c = contect.get();
            c.setStatus(EasySchoolConsent.close);
            c.setUpdateBy(name);
            c.setUpdateAt(LocalDateTime.now());
            contectRpo.save(c);
        }
    }
}
