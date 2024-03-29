package com.fldmvc.action;

import com.fldmvc.model.Fld;
import com.fldmvc.model.Member;
import com.fldmvc.service.FldService;
import com.fldmvc.service.MemService;
import com.fldmvc.service.MemServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/member")
public class MemberAction {
    Logger log = Logger.getLogger(MemberAction.class);

    //  MemService memService = new MemServiceImpl();
    @Autowired
    MemService memService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    private String login() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    private void login(HttpServletRequest req, HttpServletResponse resp, Member member) throws IOException {
        log.debug("login");
        if (memService.login(member)) {
            req.getSession().setAttribute("isLogin", member);
            resp.getWriter().print("success");
        } else {
            req.getSession().setAttribute("isLogin", null);
            resp.getWriter().print("failure");
        }
    }

    @RequestMapping("/logout")
    private void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().setAttribute("isLogin", null);
        resp.sendRedirect("anyurl");
    }


}
