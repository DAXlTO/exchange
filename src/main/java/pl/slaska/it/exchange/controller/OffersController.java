package pl.slaska.it.exchange.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.slaska.it.exchange.dao.OffersDAO;
import pl.slaska.it.exchange.dao.UsersDAO;
import pl.slaska.it.exchange.dao.WalletDAO;
import pl.slaska.it.exchange.model.Offers;
import pl.slaska.it.exchange.model.UserDetails;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/offers")
public class OffersController {

    private OffersDAO offersDAO;
    private UsersDAO usersDAO;
    private WalletDAO walletDAO;

    @Autowired
    public void setOffersDAO(UsersDAO usersDAO, OffersDAO offersDAO, WalletDAO walletDAO){
        this.usersDAO = usersDAO;
        this.offersDAO = offersDAO;
        this.walletDAO = walletDAO;
    }


    @RequestMapping(value="/sell", method = RequestMethod.GET)
    public String userSell(Model model, HttpSession session) {
        if (session.getAttribute("user") == null) {
            UserDetails a= new UserDetails();
            model.addAttribute("user", a);
            return "login";
        }
        UserDetails user = (UserDetails) session.getAttribute("user");
        model.addAttribute("user", usersDAO.getUser(user.getId()));
        model.addAttribute("offer",new Offers());
        return "offers/sell";
    }

    @RequestMapping(value="/sell", method = RequestMethod.POST)
    public String usersSell(@ModelAttribute("offer") Offers offer, BindingResult bindingResult, HttpSession session) {
        UserDetails user = (UserDetails) session.getAttribute("user");
        OffersValidator offersValidator = new OffersValidator();
        offersValidator.validate1(walletDAO.getWalletByID(user.getId()).getQuantity(),offer,bindingResult);
        if(bindingResult.hasErrors())
            return "offers/sell";
        offersDAO.addOffer((float) (offer.getQuantity()-offer.getQuantity()*0.005),offer.getPrice(),user.getId());
        return "redirect:/users/home";
    }
}
