package org.easyschool.service;

import lombok.extern.slf4j.Slf4j;
import org.easyschool.Model.Contect;
import org.easyschool.Model.getpros;
import org.easyschool.Repository.contectRpo;
import org.easyschool.Consents.EasySchoolConsent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class contectservice {

    @Autowired
    contectRpo contectRpo;

    @Autowired
    getpros getpros;


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

   public Page<Contect> getData(int pageNum, String sortField, String sortDir){

        int pageSize=getpros.getPageSize();
       // if(getpros.getC)
       Pageable pageable = PageRequest.of(pageNum-1, pageSize,
               sortDir.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending());
         return contectRpo.findByStatus(EasySchoolConsent.open,pageable);
    }

    public void updateMsgClose(int id, String name){

           contectRpo.updateMsgStatusNative(EasySchoolConsent.close,id);

    }

}
