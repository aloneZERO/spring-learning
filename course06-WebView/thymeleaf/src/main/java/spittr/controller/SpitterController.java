package spittr.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/spitter")
public class SpitterController {

    private SpitterDao spitterDao;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("spitter", new Spitter());
        return "registerForm";
    }

    @PostMapping("/register")
    public String processRegistration(
            @Valid Spitter spitter, Errors errors)
            throws UnsupportedEncodingException {
        log.info("{}", errors.getFieldErrors());

        if (errors.hasErrors()) {
            return "registerForm";
        }

        spitterDao.save(spitter);
        return "redirect:/spitter/" +
                URLEncoder.encode(spitter.getUsername(), "utf-8");
    }

    @GetMapping("/{username}")
    public String showSpitterProfile(
            @PathVariable("username") String username,
            Model model) {
        Spitter spitter = spitterDao.findByUsername(username);
        model.addAttribute("spitter", spitter);
        return "profile";
    }

}
