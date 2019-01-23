package spittr.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import spittr.dao.SpitterDao;
import spittr.pojo.Spitter;

import javax.validation.Valid;

@Controller
@RequestMapping("/spitter")
@AllArgsConstructor
public class SpitterController {

    private SpitterDao spitterDao;

    @PostMapping("/register")
    public String processRegistration(
            @Valid Spitter spitter, Errors errors) {
        if (errors.hasErrors()) {
            return "registerForm";
        }
        spitterDao.save(spitter);
        return "redirect:/spitter/" + spitter.getUsername();
    }

    @GetMapping("/{username}")
    public String showSpitterProfile(
            @PathVariable String username, Model model) {
        Spitter spitter = spitterDao.findByUsername(username);
        model.addAttribute(spitter);
        return "profile";
    }

}
