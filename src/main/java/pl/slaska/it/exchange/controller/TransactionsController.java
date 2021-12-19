package pl.slaska.it.exchange.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.slaska.it.exchange.dao.TransactionsDAO;
import pl.slaska.it.exchange.dao.UsersDAO;
import pl.slaska.it.exchange.dao.WalletDAO;
import pl.slaska.it.exchange.model.Transactions;
import pl.slaska.it.exchange.model.UserDetails;
import pl.slaska.it.exchange.model.Wallet;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/transactions")
public class TransactionsController {

    private UsersDAO usersDAO;
    private WalletDAO walletDAO;
    private TransactionsDAO transactionsDAO;

    @Autowired
    public void setUsersDAO(UsersDAO usersDAO, WalletDAO walletDAO, TransactionsDAO transactionsDAO){
        this.usersDAO = usersDAO;
        this.walletDAO = walletDAO;
        this.transactionsDAO = transactionsDAO;
    }

    @RequestMapping("/list")
    public String listTransacctions(HttpSession session, Model model){
        UserDetails user = (UserDetails) session.getAttribute("user");

        model.addAttribute("transactions", transactionsDAO.getTransactions(walletDAO.getWalletByID(user.getId()).getIdWallet()));
        return "transactions/list";
    }

    @RequestMapping(value="/send", method = RequestMethod.GET)
    public String userSend(HttpSession session, Model model) {
        if (session.getAttribute("user") == null) {
            UserDetails a= new UserDetails();
            model.addAttribute("user", a);
            return "login";
        }
        UserDetails user = (UserDetails) session.getAttribute("user");

        model.addAttribute("user", usersDAO.getUser(user.getId()));
        model.addAttribute("wallet",walletDAO.getWalletByID(user.getId()));
        model.addAttribute("transaction",new Transactions());
        return "transactions/send";
    }


    @RequestMapping(value="/send", method = RequestMethod.POST)
    public String processSendSubmit(@ModelAttribute("transaction") Transactions transaction ,BindingResult bindingResult, HttpSession session) {
        UserDetails user = (UserDetails) session.getAttribute("user");
        TransactionsValidator transactionsValidator = new TransactionsValidator();
        transactionsValidator.validate1(walletDAO.getWalletByID(user.getId()).getQuantity(),transaction,bindingResult);
        if (bindingResult.hasErrors())
            return "transactions/send";

        transactionsDAO.addTransaction(transaction.getWalletBuyer(),walletDAO.getWalletByID(user.getId()).getIdWallet(),transaction.getQuantity());
        walletDAO.updateWallet(walletDAO.getWalletByID(user.getId()).getQuantity()-transaction.getQuantity(),walletDAO.getWalletByID(user.getId()).getIdWallet());
        if(walletDAO.getWallet(transaction.getWalletBuyer()) != null)
            walletDAO.updateWallet((float) (walletDAO.getWallet(transaction.getWalletBuyer()).getQuantity() + (transaction.getQuantity() - transaction.getQuantity()*0.01 )),transaction.getWalletBuyer());
        return "redirect:/users/home";
    }
}
