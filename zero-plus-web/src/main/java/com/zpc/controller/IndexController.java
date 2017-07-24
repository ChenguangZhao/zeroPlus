package com.zpc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Title:
 * @Description:
 * @author: chenguang.zcg
 * @version:1.1.0
 * @date 2017/07/19
 */
@Controller
public class IndexController {

    /**
     * web
     *
     * @return
     */
    @RequestMapping({"/web/*"})
    public String index() {
        return "default";
    }

    /**
     * welcome
     *
     * @return
     */
    @RequestMapping({"/"})
    public String root() {
        return "index";
    }



}
