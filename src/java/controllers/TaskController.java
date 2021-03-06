
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.RoleDAO;
import daos.TaskDAO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Role;
import models.Task;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import services.TaskService;
import services.interfaces.ITaskService;
import util.MethodIdentifier;

/**
 * Maps to tasks.htm
 *
 * @author Nilesh
 */
public class TaskController implements Controller {

    ITaskService tsk = new TaskService();

    /**
     * Main Request Handler for this controller
     *
     * @param hsr
     * @param hsr1
     * @return
     * @throws Exception
     */
    @Override
    public ModelAndView handleRequest(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {

        ModelAndView mv = new ModelAndView("tasks");
       

        String method = MethodIdentifier.identifyMethod(hsr);

        switch (method) {
            case "GET":
                mv.addObject("flag", false);
                break;

            case "POST":
                tsk.saveTask(hsr.getParameter("desc"));

                mv.addObject("flag", true);
                mv.addObject("message", "Task Added Successfully!");
                break;

            case "PUT":
                tsk.updateTask(Integer.parseInt(hsr.getParameter("id")),
                        hsr.getParameter("desc"));

                mv.addObject("flag", true);
                mv.addObject("message", "Task Edited Successfully!");
                break;

            case "DELETE":
                break;
        }

        get(mv);
        mv.addObject("page", "task");
        return mv;
    }

    /**
     * Generate view for Task List
     *
     * @return
     */
    private ModelAndView get(   ModelAndView mv ) {

        try {
            mv.addObject("tasks", tsk.getTasks());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }
}

