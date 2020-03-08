package com.alipay.sofa.boot.examples.lucene;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

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
    private DemoBizService demoBizService;

    @RequestMapping("/search/{id}")
    public @ResponseBody Object searchById(@PathVariable("id") String id) {
        logger.info("http searchById: {}", id);
        return demoBizService.searchById(id);
    }

    @RequestMapping("/search")
    public @ResponseBody Object searchByFirstName(@RequestParam("firstName") String firstName) {
        logger.info("http searchByFirstName: {}", firstName);
        return demoBizService.searchByFirstName(firstName);
    }
}
