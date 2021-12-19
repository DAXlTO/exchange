package pl.slaska.it.exchange.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.slaska.it.exchange.dao.UsersDAO;
import pl.slaska.it.exchange.dao.WalletDAO;
import pl.slaska.it.exchange.model.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/users")
public class UsersController {

    private UsersDAO usersDAO;
    private WalletDAO walletDAO;

    @Autowired
    public void setUsersDAO(UsersDAO usersDAO, WalletDAO walletDAO){
        this.usersDAO = usersDAO;
        this.walletDAO = walletDAO;
    }

    @RequestMapping(value = "/add")
    public String addUsers(Model model){
        model.addAttribute("users",new Users());
        return "users/add";
    }

    @RequestMapping(value="/add", method= RequestMethod.POST)
    public String processAddSubmit(@ModelAttribute("users") Users users, BindingResult bindingResult) {
        UsersValidator usersValidator = new UsersValidator();
        usersValidator.validate(users,bindingResult);
        if (bindingResult.hasErrors())
            return "users/add";
        try {
            usersDAO.addUser(users);
        }
        catch (DuplicateKeyException e ){
            bindingResult.rejectValue("id","id","ID Duplicated");
                return "users/add";
        }
        return "/login";
    }

    @RequestMapping(value = "/home")
    public String listSocis(HttpSession session, Model model) {
        if (session.getAttribute("user") == null) {
            UserDetails a= new UserDetails();
            model.addAttribute("user", a);
            return "login";
        }
        UserDetails user = (UserDetails) session.getAttribute("user");
        model.addAttribute("user", usersDAO.getUser(user.getId()));
        model.addAttribute("wallet",walletDAO.getWalletByID(user.getId()));
        return "users/home";
    }

    @RequestMapping(value = "/profile")
    public String profile(HttpSession session, Model model) {
        if (session.getAttribute("user") == null) {
            UserDetails a= new UserDetails();
            model.addAttribute("user", a);
            return "login";
        }
        UserDetails user = (UserDetails) session.getAttribute("user");
        model.addAttribute("user", usersDAO.getUser(user.getId()));
        model.addAttribute("wallet",walletDAO.getWalletByID(user.getId()));
        return "users/profile";
    }

    @RequestMapping(value="/update", method = RequestMethod.GET)
    public String editCiudadano(Model model, HttpSession session) {
        if (session.getAttribute("user") == null) {
            UserDetails a= new UserDetails();
            model.addAttribute("user", a);
            return "login";
        }
        UserDetails user = (UserDetails) session.getAttribute("user");
        model.addAttribute("user", usersDAO.getUser(user.getId()));
        return "users/update";
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    public String processUpdateSubmit(@ModelAttribute("users") Users ciudadano, BindingResult bindingResult) {
        UsersValidator usersValidator=new UsersValidator();
        usersValidator.validate(ciudadano,bindingResult);
        if (bindingResult.hasErrors())
            return "users/update";
        usersDAO.updateUser(ciudadano);
        return "redirect:/users/home";
    }

    @RequestMapping(value="/balance", method = RequestMethod.GET)
    public String addBalance(Model model, HttpSession session) {
        if (session.getAttribute("user") == null) {
            UserDetails a= new UserDetails();
            model.addAttribute("user", a);
            return "login";
        }
        UserDetails user = (UserDetails) session.getAttribute("user");
        model.addAttribute("user", usersDAO.getUser(user.getId()));
        return "users/balance";
    }

    @RequestMapping(value="/balance", method = RequestMethod.POST)
    public String processBalanceSubmit(@ModelAttribute("users") Users users, BindingResult bindingResult) {
        UsersValidator usersValidator=new UsersValidator();
        usersValidator.validate(users,bindingResult);
        if (bindingResult.hasErrors())
            return "users/balance";
        usersDAO.updateBalance(users);
        return "redirect:/users/home";
    }

    @RequestMapping(value="/buy", method = RequestMethod.GET)
    public String userBuy(Model model, HttpSession session) {
        if (session.getAttribute("user") == null) {
            UserDetails a= new UserDetails();
            model.addAttribute("user", a);
            return "login";
        }
        UserDetails user = (UserDetails) session.getAttribute("user");
        model.addAttribute("user", usersDAO.getUser(user.getId()) );
        return "users/buy";
    }

    @RequestMapping(value="/buy", method = RequestMethod.POST)
    public String usersBuy(@ModelAttribute("users") Users users, BindingResult bindingResult) {
        UsersValidator usersValidator=new UsersValidator();
        usersValidator.validate(users,bindingResult);
        if (bindingResult.hasErrors())
            return "users/buy";
        usersDAO.updateUser(users);
        return "redirect:/users/home";
    }


}
