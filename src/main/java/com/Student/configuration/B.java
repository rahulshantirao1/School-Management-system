package com.Student.configuration;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class B {
    private RestTemplate restTemplate;


    public B(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public static  void main(String[] args){
//        restTemplate.getForObject("url_ofApi",ClassTypeWhere.class);
//        HttpEntity<Object> o = new HttpEntity<>();
//        restTemplate.exchange("ht", HttpMethod.POST,o,ClassHnadlernmwe)
//        restTemplate.postForObject("apiurl","",HandleClass);
//
      // ArraysList<Integer>arraysList=new ArrayList<Integer>();

        List<Integer> list = Arrays.asList(3, 4, 5, 63, 4, 8);
        list.stream().map(e -> e)
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()))
                .entrySet().stream()
                .filter(t->t.getValue()>1)
                .forEach(s->System.out.println(s.getKey()));

        //   select distinct salary from employee order by salary desc limit 4,1
        //select salary from employee where salary count(salary)>1
    }
}
