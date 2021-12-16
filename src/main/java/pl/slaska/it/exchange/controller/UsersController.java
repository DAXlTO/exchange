package pl.slaska.it.exchange.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.slaska.it.exchange.dao.UsersDAO;
import pl.slaska.it.exchange.dao.WalletDAO;
import pl.slaska.it.exchange.model.Offers;
import pl.slaska.it.exchange.model.UserDetails;
import pl.slaska.it.exchange.model.Users;
import pl.slaska.it.exchange.model.Wallet;

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
        model.addAttribute("wallet",walletDAO.getWallet(user.getId()));
        return "users/home";
    }

    @RequestMapping(value = "/profile/{id}")
    public String profile(HttpSession session, Model model, @PathVariable String id) {
        if (session.getAttribute("user") == null) {
            UserDetails a= new UserDetails();
            model.addAttribute("user", a);
            return "login";
        }
        model.addAttribute("user", usersDAO.getUser(id));
        model.addAttribute("wallet",walletDAO.getWallet(id));
        return "users/profile";
    }

    @RequestMapping(value="/update/{id}", method = RequestMethod.GET)
    public String editCiudadano(Model model, @PathVariable String id) {
        model.addAttribute("users", usersDAO.getUser(id) );
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

    @RequestMapping(value="/balance/{id}", method = RequestMethod.GET)
    public String addBalance(Model model, @PathVariable String id) {
        model.addAttribute("users", usersDAO.getUser(id) );
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

    @RequestMapping(value="/buy/{id}", method = RequestMethod.GET)
    public String userBuy(Model model, @PathVariable String id) {
        model.addAttribute("users", usersDAO.getUser(id) );
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

    @RequestMapping(value="/sell/{id}", method = RequestMethod.GET)
    public String userSell(Model model, @PathVariable String id) {
        model.addAttribute("users", usersDAO.getUser(id));
        model.addAttribute("wallet",walletDAO.getWallet(id));
        model.addAttribute("offer",new Offers());

        return "users/sell";
    }

    @RequestMapping(value="/sell", method = RequestMethod.POST)
    public String usersSell(@ModelAttribute("users") Users users, BindingResult bindingResult) {
        UsersValidator usersValidator=new UsersValidator();
        usersValidator.validate(users,bindingResult);
        if (bindingResult.hasErrors())
            return "users/sell";
        usersDAO.updateUser(users);
        return "redirect:/users/home";
    }

}
