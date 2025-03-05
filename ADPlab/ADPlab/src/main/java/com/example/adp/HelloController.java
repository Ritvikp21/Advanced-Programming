package com.example.adp;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
class HelloController {
    //private final TreeMap<Long, MyPOJO> map = new TreeMap<>();

    private final PojoRepository pojoRepository;

    public HelloController(PojoRepository pojoRepository){
        this.pojoRepository = pojoRepository;
    }
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
        return this.pojoRepository.findAll();
    }

    @GetMapping("/pojo/{id}")
    public MyPOJO getById(@PathVariable("id") Long id) {
        return this.pojoRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
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
        body.setIdNumber(0); // clear any ID number to prevent update

        return this.pojoRepository.save(body);
    }


    @PutMapping("/pojo")
    public MyPOJO putPojo(@RequestBody MyPOJO body) {
        return this.pojoRepository.save(body);
    }

    public String[] deletePojo(@PathVariable long id) {
        if (this.pojoRepository.existsById(id)) {
            this.pojoRepository.deleteById(id);
            return new String[] {"item removed"};
        } else {
            return new String[] {"item not found"};
        }
    }

    @PostMapping("pojos")
    public List<MyPOJO> postABunch(@RequestBody List<MyPOJO> list) {
        List<MyPOJO> returnList = new ArrayList<>(list.size());
        for(MyPOJO pojo : list) {
            pojo.setIdNumber(0);
            returnList.add(this.pojoRepository.save(pojo));
        }
        return returnList;
    }
}
