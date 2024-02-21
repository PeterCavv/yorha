package com.dataproject.yorha.controller;

import com.dataproject.yorha.DTO.TypeDTO;
import com.dataproject.yorha.entity.Type;
import com.dataproject.yorha.service.TypeService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/types")
public class TypeController {
    @Autowired
    private TypeService typeService;

    @GetMapping
    public ResponseEntity<List<Type>> getAllTypes(){
        return new ResponseEntity<List<Type>>(typeService.allTypes(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Type>> getOneType(@PathVariable String id){
        return new ResponseEntity<Optional<Type>>(typeService.oneType(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Type> postType(@RequestBody @Validated TypeDTO typeDTO) {
        Type type = typeService.createType(typeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(type );
    }

    @DeleteMapping(value = "/delete/{name}")
    public ResponseEntity<HttpStatus> deleteType(@PathVariable String name){
        typeService.delete(name);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
