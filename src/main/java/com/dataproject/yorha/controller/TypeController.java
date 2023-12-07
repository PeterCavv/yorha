package com.dataproject.yorha.controller;

import com.dataproject.yorha.model.Type;
import com.dataproject.yorha.service.TypeService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/types")
public class TypeController {
    @Autowired
    private TypeService typeService;

    @GetMapping
    public ResponseEntity<List<Type>> getAllTypes(){
        return new ResponseEntity<List<Type>>(typeService.allTypes(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Type>> getOneType(@PathVariable ObjectId id){
        return new ResponseEntity<Optional<Type>>(typeService.oneType(id), HttpStatus.OK);
    }

    @PostMapping("/create/{name}/{resume}/{desc}")
    public ResponseEntity<Type> postType(@PathVariable("name") String name, @PathVariable("resume") String resume,
                                           @PathVariable("desc") String desc) {
        return new ResponseEntity<Type>(typeService.save(name, resume, desc), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/delete/{name}")
    public ResponseEntity<HttpStatus> deleteType(@PathVariable String name){
        typeService.delete(name);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
