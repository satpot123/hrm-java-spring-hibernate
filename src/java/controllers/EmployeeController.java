 
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import services.EmployeeService;
import services.RoleService;
import services.interfaces.IEmployeeService;
import util.MethodIdentifier;

/**
 * Maps to route employee.htm
 *
 * @author Nilesh
 */
public class EmployeeController implements Controller {

       IEmployeeService emps = new EmployeeService();
    /**
     * Handle requests
     *
     * @param hsr
     * @param hsr1
     * @return
     * @throws Exception
     */
    @Override
    public ModelAndView handleRequest(HttpServletRequest hsr, HttpServletResponse hsr1) {

         ModelAndView mv = new ModelAndView("employees");

        String method = MethodIdentifier.identifyMethod(hsr);

        switch (method) {
            case "GET":
                mv.addObject("flag", false);
                break;

            case "POST":
                emps.saveEmployee(Integer.parseInt(hsr.getParameter("role")), hsr.getParameter("name"));
                mv.addObject("flag", true);
                mv.addObject("message", "Employee Added Successfully!");
                break;

        }
        get(mv);
        mv.addObject("page", "employee");
        return mv;
    }

    /**
     * Generate get view
     * @return 
     */
    public void get(ModelAndView mv) {
       
        
        try {
            mv.addObject("roles", new RoleService().getRoles());
            mv.addObject("employees", emps.getEmployeeList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

}
 
