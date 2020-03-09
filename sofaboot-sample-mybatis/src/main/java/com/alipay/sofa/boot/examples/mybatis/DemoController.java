package com.alipay.sofa.boot.examples.mybatis;

import javax.annotation.Resource;

import com.alipay.sofa.boot.examples.mybatis.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * DemoController
 *
 * @author Deven
 * @version : DemoController, v 0.1 2020-03-08 22:17 Deven Exp$
 */
@RequestMapping("/demo")
@Controller
public class DemoController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private StudentService studentService;

    @RequestMapping("/search/{id}")
    public @ResponseBody Object searchById(@PathVariable("id") Integer id) {
        logger.info("http searchById: {}", id);
        return studentService.findById(id);
    }

    @RequestMapping("/search")
    public @ResponseBody Object searchByName(@RequestParam("name") String name) {
        logger.info("http searchByName: {}", name);
        return studentService.findByName(name, false);
    }
}
