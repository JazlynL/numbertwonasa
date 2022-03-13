package com.careerdevs.numbertwonasa.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

//RestController is a Spring annotation that is used to build REST API in a declarative way.
@RestController
// is our http method diagnostic, its an annotation for our specific path but not look for a specific http method
//we always use request mapping for controller but not for a route handling.
@RequestMapping("/api/apod")
public class ApodController {

    // this is  field is created to access the  values within application properties
    @Autowired
    private Environment env;

    // Url http:localhost:3600/api/apod/image
    // url http://localhost:3600/api/apod/image?year=2003&month=03&day=03

 // all route handlers created within the class will have api/apod/ added whatever to the end based on the method.
    @GetMapping("/image")

    public String apodImage(RestTemplate restTemplate,
    @RequestParam("year") String year,
    @RequestParam("month") String month,
    @RequestParam("day") String day



    ){
        String date = year +"-"+ month+"-"+day;
        //this is how we are able to access the values within the application properties class.
        String apodKey = env.getProperty("APOD_KEY", "cWvDm2947vDzndUC6Y0hgYVBH5zzgE6vxDnpL2Ue");
        String url = "https://api.nasa.gov/planetary/apod?api_key=" + apodKey;
        url+="&date="+date;

        // this how we are going store and retrieve the data value
        Apodfields response = restTemplate.getForObject(url,Apodfields.class);
        System.out.println("\n\n"+response.getUrl());

        return response.getUrl();
    }
}
