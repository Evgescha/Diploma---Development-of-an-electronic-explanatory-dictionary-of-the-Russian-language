package com.hescha.rudictionary.controller;

import com.hescha.rudictionary.model.DictionaryType;
import com.hescha.rudictionary.model.Word;
import com.hescha.rudictionary.service.DictionaryTypeService;
import com.hescha.rudictionary.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Controller
@RequiredArgsConstructor
@RequestMapping(WordController.CURRENT_ADDRESS)
public class WordController {
    public static final String API_PATH = "word";
    public static final String CURRENT_ADDRESS = "/" + API_PATH;
    public static final String MESSAGE = "message";
    public static final String THYMELEAF_TEMPLATE_ALL_ITEMS_PAGE = API_PATH;
    public static final String THYMELEAF_TEMPLATE_ONE_ITEM_PAGE = THYMELEAF_TEMPLATE_ALL_ITEMS_PAGE + "-one";
    public static final String THYMELEAF_TEMPLATE_EDIT_PAGE = THYMELEAF_TEMPLATE_ALL_ITEMS_PAGE + "-edit";
    public static final String REDIRECT_TO_ALL_ITEMS = "redirect:" + CURRENT_ADDRESS;

    private final WordService service;
    private final DictionaryTypeService dictionaryTypeService;

    @GetMapping
    public String readAll(@RequestParam(name = "page", defaultValue = "0") int page,
                          @RequestParam(name = "size", defaultValue = "5") int size,
                          Model model) {
        if(page<0)page=0;
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<Word> wordsPage = service.readAll(pageable);
        model.addAttribute("list", wordsPage.getContent());
        model.addAttribute("page", wordsPage);
        return THYMELEAF_TEMPLATE_ALL_ITEMS_PAGE;
    }


    @GetMapping("/{id}")
    public String read(@PathVariable("id") Long id, Model model) {
        model.addAttribute("entity", service.read(id));
        return THYMELEAF_TEMPLATE_ONE_ITEM_PAGE;
    }

    @PostMapping("/search")
    public String read(@RequestParam String name, Model model) {
        model.addAttribute("list", service.findByNameContains(name));
        return "search_result";
    }

    @GetMapping(path = {"/edit", "/edit/{id}"})
    public String editPage(Model model, @PathVariable(name = "id", required = false) Long id) {
        if (id == null) {
            model.addAttribute("entity", new Word());
        } else {
            model.addAttribute("entity", service.read(id));
        }
        model.addAttribute("dictionary_list", dictionaryTypeService.readAll());
        return THYMELEAF_TEMPLATE_EDIT_PAGE;
    }

    @PostMapping
    public String save(@ModelAttribute Word entity, RedirectAttributes ra) {
        if (entity.getId() == null) {
            try {
                Word createdEntity = service.create(entity);
                ra.addFlashAttribute(MESSAGE, "Creating is successful");
                return REDIRECT_TO_ALL_ITEMS + "/" + createdEntity.getId();
            } catch (Exception e) {
                ra.addFlashAttribute(MESSAGE, "Creating failed");
                e.printStackTrace();
            }
            return REDIRECT_TO_ALL_ITEMS;
        } else {
            try {
                service.update(entity.getId(), entity);
                ra.addFlashAttribute(MESSAGE, "Editing is successful");
            } catch (Exception e) {
                e.printStackTrace();
                ra.addFlashAttribute(MESSAGE, "Editing failed");
            }
            return REDIRECT_TO_ALL_ITEMS + "/" + entity.getId();
        }
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

    @GetMapping("/addWords")
    public String addWords(Model model) {
        model.addAttribute("dictionary_list", dictionaryTypeService.readAll());
        return "word-add";
    }

    @PostMapping("/addWords")
    public String addManyWords(@RequestParam("id") Long id,
                               @RequestParam("text") String text) {

        Pattern pattern = Pattern.compile("^\\p{Lu}", Pattern.MULTILINE);
        Map<String, String> wordDescriptions =  Stream.of(text.split("\\n"))
                .filter(line -> pattern.matcher(line).find())
                .map(line -> line.split("\\s+", 2))
                .collect(Collectors.toMap(
                        parts -> parts[0],
                        parts -> parts.length > 1 ? parts[1] : ""));

        wordDescriptions.forEach((word, description) -> {
            DictionaryType dictionaryType = dictionaryTypeService.read(id);
                Word entity = new Word();
                entity.setName(word.trim());
                entity.setDescription(description);
                entity.setDictionaryType(dictionaryType);
                Word word1 = service.create(entity);
                dictionaryType.getWords().add(word1);
                dictionaryTypeService.update(dictionaryType);
        });

        return REDIRECT_TO_ALL_ITEMS;
    }
}
