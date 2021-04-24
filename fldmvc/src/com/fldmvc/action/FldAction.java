package  com.fldmvc.action;


import com.fldmvc.mapper.FldMapper;
import com.fldmvc.model.Fld;
import com.fldmvc.service.FldService;
import com.fldmvc.service.FldServiceImpl;
import com.fldmvc.util.CRUD;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Controller
@RequestMapping("/fldAction")
public class FldAction  {
    Logger log=Logger.getLogger(FldAction.class);
    @Autowired
    FldService fldService;  //=new FldServiceImpl();

    @RequestMapping()
    private String select(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String fldb= req.getParameter("fldb");
        if(fldb==null) fldb="";
        req.getSession().setAttribute("lstFld",fldService.getFld(fldb));
        log.debug(fldService.getFld(fldb).size());
       //  resp.sendRedirect("show.jsp");
        return  "show";
    }

    private void ajaxdel(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int flda=Integer.parseInt(  req.getParameter("flda") );
        if( fldService.deleteFld(flda)){
          resp.getWriter().print("success");
        }else{
            resp.getWriter().print("failure");
        }
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int flda=Integer.parseInt(  req.getParameter("flda") );
        fldService.deleteFld(flda);
        resp.sendRedirect("fldAction");
    }

    private void check(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int flda=Integer.parseInt(  req.getParameter("flda") );
        if(fldService.checkFlda(flda)){
            resp.getWriter().print("exist");
        }else{
            resp.getWriter().print("no");
        }
    }



    private void insert(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int flda=Integer.parseInt(  req.getParameter("flda") );
        String fldb= req.getParameter("fldb");
        float fldc=Float.parseFloat(  req.getParameter("fldc") );
        String fldd= req.getParameter("fldd");
        if (fldService.insertFld(new Fld(flda,fldb,fldc,fldd))){
            resp.sendRedirect("fldAction");
        }else{
            req.setAttribute("error","failure");
            req.getRequestDispatcher("index.jsp").forward(req,resp);
        }
    }
}
