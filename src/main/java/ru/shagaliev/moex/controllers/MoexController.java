package ru.shagaliev.moex.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.shagaliev.moex.dao.MoexDao;

@Controller
@RequestMapping("/moex")
public class MoexController {
    private final MoexDao moexDao;

    public MoexController(MoexDao moexDao) {
        this.moexDao = moexDao;
    }

    @GetMapping()
    public String index(Model model) {
        //поулчим весь акции и передадим на отображение
        model.addAttribute("moex",moexDao.index());
        return "moex/index";
    }

    @GetMapping("/{ticket}")
    public String show(@PathVariable("ticket") String ticket, Model model){
        model.addAttribute("share",moexDao.show(ticket));
        return "moex/share";
    }
}
