package cn.ac.iie.controller;

import cn.ac.iie.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.Map;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    BaseService baseService;

    @ResponseBody
    @RequestMapping(value="index",method={RequestMethod.GET, RequestMethod.POST})
    public String test(){
        String query =
                "query find($a: string){\n" +
                        "  find(func: eq(name@., $a)) {\n" +
                        "    directed_by{expand(_all_)}\n" +
                        "  }\n" +
                        "}\n";
        Map<String, String> vars = Collections.singletonMap("$a", "Smert Tairova");
        String s = baseService.baseQuery(query, vars);
        System.out.println(s);
        return s;
    }
}
