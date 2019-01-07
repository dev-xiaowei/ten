package com.tensquare.base.controller;

import com.tensquare.base.pojo.People;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping(value = "/people", method = RequestMethod.POST)
    public Result testPeople( @RequestBody People people, HttpServletRequest request) {
        String name1 =  request.getParameter("name");
        String name = people.getName();
        Integer age = people.getAge();
        System.out.println(name+"=="+age);

        return new Result(true, 200, "success");
    }

    @RequestMapping(value = "/people2", method = RequestMethod.GET)
    public Result testInteger(Integer id, HttpServletRequest request) {

        System.out.println(id+"====");
        return new Result(true, 200, "success");
    }

    @RequestMapping(value = "/people3", method = RequestMethod.POST)
    public Result testPeople3(@RequestBody Map<String, Object> map) {
        String name = (String) map.get("name");
        People people = (People) map.get("people");
        System.out.println(name);
        return new Result(true, 200, "success");
    }

}
