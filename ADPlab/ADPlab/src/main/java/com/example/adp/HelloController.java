package com.example.adp;

import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeMap;

@RestController
class HelloController {
    private final TreeMap<Long, MyPOJO> map = new TreeMap<>();

    @GetMapping("/")
    public String hello() {
        return "hello world!";
    }

    @GetMapping("/helloagain")
    public String[] helloAgain() {
        return new String[]{"hello world!", "again", "hang on what's this?"};
    }

//    @GetMapping("/pojo")
//    public MyPOJO[] myPojo() {
//        return new MyPOJO[] {
//                new MyPOJO("John", "Smith", 1234567),
//                new MyPOJO("Sarah", "Brown", 7654321) };
//    }

    @GetMapping("/pojo")
    public Collection<MyPOJO> getAll() {
        return map.values();
    }

    @GetMapping("/pojo/{id}")
    public MyPOJO getById(@PathVariable("id") Long id) {
        return map.get(id);
    }

    @GetMapping("/pojo/{first}/{last}/{id}")
    public MyPOJO myPojo(@PathVariable("first") String firstName,
                         @PathVariable("last") String lastName,
                         @PathVariable("id") int idNumber) {
        return new MyPOJO(firstName, lastName, idNumber);
    }

    @GetMapping("/modules")
    public Modules[] modules() {
        return new Modules[]{
                new Modules("Advanced Programming", 3867482)};
    }

    @PostMapping("/pojo")
    public MyPOJO postPojo(@RequestBody MyPOJO body) {
        long nextId = 1;
        if (!this.map.isEmpty()) {
            nextId = ((SortedSet<Long>) this.map.keySet()).last() + 1;
        }
        MyPOJO newItem = new MyPOJO(body.getFirstName(), body.getLastName(), nextId);
        this.map.put(nextId, newItem);
        return newItem;
    }

    @PutMapping("/pojo")
    public MyPOJO putPojo(@RequestBody MyPOJO body) {
        this.map.put(body.getIdNumber(), body);
        return body;
    }

    @DeleteMapping("/pojo/{id}")
    public String[] deletePojo(@PathVariable long id) {
        if (this.map.remove(id) != null) {
            return new String[]{"item removed"};
        } else {
            return new String[]{"item not found"};
        }
    }
}
