package ch.zhaw.mas.sharingApp.clientSite.firstTestProjectSetup.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("books")
public class Test {

    @GetMapping("/test")
    public @ResponseBody
    void getBook() {
        System.out.println("bla");
    }

}
