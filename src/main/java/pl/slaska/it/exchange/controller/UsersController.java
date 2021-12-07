package pl.slaska.it.exchange.controller;


import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.slaska.it.exchange.dao.UsersDAO;
import pl.slaska.it.exchange.model.Users;

@Controller
@RequestMapping("/users")
public class UsersController {

    private UsersDAO usersDAO;

    @Autowired
    public void setUsersDAO(UsersDAO usersDAO){this.usersDAO = usersDAO;}

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
            usersDAO.addCiudadano(users);
        }
        catch (DuplicateKeyException e ){
            throw new DuplciatedKeyException("Ya existe el NIF " + users.getId(),"CPduplicada");
        }
        return "/login";
    }

}
