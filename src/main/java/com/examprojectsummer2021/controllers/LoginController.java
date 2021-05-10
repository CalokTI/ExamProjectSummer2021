package com.examprojectsummer2021.controllers;

import com.examprojectsummer2021.services.ValidateLoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Carsten
 * Time: 12.37
 * Date: 06/05/2021
 */

@Controller
public class LoginController {

    ValidateLoginService loginService = new ValidateLoginService();

    @GetMapping("/login")
    public String renderLogin(HttpServletRequest request, Model model){

        HttpSession session = request.getSession();
        boolean loginFailed = (boolean) session.getAttribute("fail"); //måske fejler den her

        model.addAttribute("loginFailed", loginFailed);

        return "login.html";
    }

    @PostMapping("/validateLogin")
    public String validateLogin(@RequestParam(name = "username") String username,
                                @RequestParam(name = "password") String password,
                                HttpServletRequest request){

        HttpSession session = request.getSession();

        boolean validLogin = loginService.validateLogin(username, password);

        if (validLogin){
            session.setAttribute("username", username);
            session.setAttribute("fail", false);
            return "redirect:/home";
        }

        session.setAttribute("fail", true);
        return "redirect:/login";
    }

}
