package com.anjusoftware.ds.acqrun.controller;

import com.anjusoftware.ds.acqrun.service.CtgovService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/api/ctgov")
public class CtgovController {
    private CtgovService ctgovService = new CtgovService();

    @GetMapping("/run")
    public ResponseEntity run() {
        File file = ctgovService.retrieveFile();

        if (file != null) {
            return ResponseEntity.ok("Retrieved file of size " + file.length());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error trying to retrieve ctgov file");
        }
    }
}
