package org.easyschool.Restapi;


import org.easyschool.Model.Contect;
import org.easyschool.Model.Response;
import org.easyschool.Repository.contectRpo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/api/app")
public class AdminRestController {

    @Autowired
    contectRpo contectRpo;

    @PostMapping("/setMsg")
    public ResponseEntity<Response> addMessgage(@RequestBody Contect contect){

        Response response = new Response();
        contectRpo.save(contect);
        response.setCode(200);
        response.setMessage("Message Successfully added ");
        return ResponseEntity.ok(response);
    }
    @PutMapping ("/updateMsg")
    public ResponseEntity<Response> updateMessgage(@RequestBody Contect contect){


            Optional<Contect> ct = contectRpo.findById(contect.getContact_id());
        ct.get().setName(contect.getName());
        Response response = new Response();
        contectRpo.save(ct.get());
        response.setCode(200);
        response.setMessage("Message updated Successfully ");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getMsgs")
    public List<Contect> getContectMgs(@RequestParam(name="status") String status){
        return contectRpo.findByStatus(status);

    }
    @GetMapping("/getMsg")
    public List<Contect> getContectMgs(@RequestBody Contect contect){

        if(contect != null && contect.getStatus() != null){
             return contectRpo.findByStatus(contect.getStatus());
        }
        else
        {
            return List.of();
        }
    }

    @PutMapping("/updatestate")
    public ResponseEntity<Response> delete(@RequestParam int contact_id, @RequestParam String status) {


            Response response = new Response();
            contectRpo.updateMsgStatus(status,contact_id);
            response.setCode(200);
            response.setMessage("Message update Successfully ");
            return ResponseEntity.ok(response);
    }

}
