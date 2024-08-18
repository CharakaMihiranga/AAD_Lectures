package lk.ijse.gdse.springweb.springweb_intro.controller;

import jakarta.servlet.http.HttpServlet;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/demo")
//The @RequestMapping annotation in Spring Web is used to map web requests to specific handler classes and/or handler methods.
// It can be applied at the class level and/or at the method level in a controller.
@RestController  // The @RestController annotation in Spring Web is a specialized version of the @Controller annotation.
// It is used to create RESTful web services. The @RestController annotation is a combination of @Controller and @ResponseBody,
// which means that it not only marks the class as a controller but also ensures that the return values of the methods are automatically
// serialized into JSON or XML and sent back to the client.
public class DemoController {
 // using this annotation we can define the URL for this method
 // using this mapping we can define the URL for this method

    @GetMapping("/health")
    public String health(){    //http://localhost:8080/SpringWebIntro/demo/health
        return "DemoController is running perfectly";
    }
    @GetMapping("/health/**")
    public String healthWithasterix(@PathVariable("name") String name){  //http://localhost:8080/SpringWebIntro/demo/health/Demo
        return "DemoController is running perfectly with: "+ name;
    }
    @GetMapping("/health/{name}")
    public String healthCheck(@PathVariable("name") String name){  //http://localhost:8080/SpringWebIntro/demo/health/Demo
        return "DemoController is running perfectly with: "+ name;
    }
    @GetMapping(value = "patternRegex/{id:C\\d{3}}") //http://localhost:8080/SpringWebIntro/demo/patternRegex/C001
    public String testRegex(@PathVariable("id") String id){
        return "Pattern Regex: "+id;
    }
    @GetMapping(params = "test=all") //http://localhost:8080/SpringWebIntro/demo?test=all
    public String getWithParams(){
        return "All are tested";
    }
    @GetMapping
    public String otherTest(){
        return "Other test";
    }
    //Methods that can get data from the client using headers
    @PostMapping(params = {"name","quantity"} )
    public String paramData(@RequestParam("name") String param1, @RequestParam("quantity") String param2) {
        return "Param data is "+ param1 + " and "+ param2;
    }
    //Custom headers with regex pattern
    @GetMapping(value = "patternRegex/{id:C\\d{3}}", headers = "X-number") //http://localhost:8080/SpringWebIntro/demo/patternRegex/C001 and add a header names X-number
    public String pathVariableWithRegexWithHeaders(@PathVariable("id") String id, @RequestHeader("X-number") String number){
        return "Pattern Regex is : "+id + " and header number is : "+number;
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE) //http://localhost:8080/SpringWebIntro/demo and set the header content type as application/json
    public String saveJSON(){
        return "JSON data saved";
    }
    @PostMapping("/dynamic/{value:\\d{2}}") //http://localhost:8080/SpringWebIntro/demo/dynamic/12
    public ResponseEntity<String> returnDynamicData( @PathVariable("value")int incomingValue ) {
        //ResponseEntity used to send additional information with the response
        if (incomingValue % 2 ==0){
            return ResponseEntity.ok("Dynamic data is even");
        }else {
            return ResponseEntity.ok("Dynamic data is odd");
        }
    }
    // get data as a map
    @PostMapping(value = "/params", params = { "id", "desc"}) //http://localhost:8080/SpringWebIntro/demo/params?id=1&desc=Test
    public String handleMaps ( @RequestParam("id") String id, @RequestParam("desc") String desc, @RequestParam Map<String, String> params) {
        System.out.println(params);
        return "Handle Maps with params: "+params;
    }
    // get data as a MultiValueMap
    @PostMapping(value = "/multimapparams", params = { "id", "desc"}) //http://localhost:8080/SpringWebIntro/demo/multimapparams?id=1&desc=Test&id=2&desc=Test2
    public String handleWithMultiMaps ( @RequestParam("id") String id, @RequestParam("desc") String desc, @RequestParam MultiValueMap<String, String> params) {
        System.out.println(params);
        return "Handle multi value maps with params: "+params;
    }
}


//Headers : Headers used to pass additional information with the request or response. It is used to pass the information about
// the client browser, the type of the body content, etc. The headers are passed in the form of key-value pairs.

//Consumes : The @Consumes annotation is used to specify the MIME media types of representations a resource can consume that were sent by the client.

//MultiValueMap : MultiValueMap is a sub-interface of Map that stores multiple values. It is used to store multiple values in a single key.
// It is used to store multiple values in a single key. It's a part of spring framework.