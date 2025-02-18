package com.app.login.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;


import java.io.IOException;
import java.nio.file.Files;

@Controller
public class StaticFileController {

    @GetMapping(value = "/css/style.css", produces = "text/css")
    @ResponseBody
    public String getCss() throws IOException {
        Resource resource = new ClassPathResource("WEB-INF/css/style.css");
        return new String(Files.readAllBytes(resource.getFile().toPath()));
    }
}
