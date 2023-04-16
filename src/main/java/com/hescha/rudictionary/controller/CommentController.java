package com.hescha.rudictionary.controller;

import com.hescha.rudictionary.model.Comment;
import com.hescha.rudictionary.model.Word;
import com.hescha.rudictionary.service.CommentService;
import com.hescha.rudictionary.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequiredArgsConstructor
@RequestMapping(CommentController.CURRENT_ADDRESS)
public class CommentController {
    public static final String API_PATH = "comment";
    public static final String CURRENT_ADDRESS = "/" + API_PATH;
    public static final String MESSAGE = "message";
    public static final String THYMELEAF_TEMPLATE_ALL_ITEMS_PAGE = API_PATH;
    public static final String THYMELEAF_TEMPLATE_ONE_ITEM_PAGE = THYMELEAF_TEMPLATE_ALL_ITEMS_PAGE + "-one";
    public static final String THYMELEAF_TEMPLATE_EDIT_PAGE = THYMELEAF_TEMPLATE_ALL_ITEMS_PAGE + "-edit";
    public static final String REDIRECT_TO_ALL_ITEMS = "redirect:" + CURRENT_ADDRESS;

    private final CommentService service;

    private final WordService wordService;

    @GetMapping
    public String readAll(Model model) {
        model.addAttribute("list", service.readAll());
        return THYMELEAF_TEMPLATE_ALL_ITEMS_PAGE;
    }

    @GetMapping("/{id}")
    public String read(@PathVariable("id") Long id, Model model) {
        model.addAttribute("entity", service.read(id));
        return THYMELEAF_TEMPLATE_ONE_ITEM_PAGE;
    }

    @PostMapping("/word/{wordId}")
    public String save(@ModelAttribute Comment entity,
                       @PathVariable Integer wordId,
                       RedirectAttributes ra) {
        Word word = wordService.read(wordId);
            try {
                Comment createdEntity = service.create(entity);
                createdEntity.setWord(word);
                createdEntity = service.update(createdEntity);
                word.getComments().add(createdEntity);
                wordService.update(word);
                ra.addFlashAttribute(MESSAGE, "Creating is successful");
            } catch (Exception e) {
                ra.addFlashAttribute(MESSAGE, "Creating failed");
                e.printStackTrace();
            }
        return "redirect:/word/"+wordId;
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        try {
            service.delete(id);
            ra.addFlashAttribute(MESSAGE, "Removing is successful");
        } catch (Exception e) {
            e.printStackTrace();
            ra.addFlashAttribute(MESSAGE, "Removing failed");
        }
        return REDIRECT_TO_ALL_ITEMS;
    }
}
