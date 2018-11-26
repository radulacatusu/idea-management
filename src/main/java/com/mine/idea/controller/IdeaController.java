package com.mine.idea.controller;

import com.mine.idea.model.Idea;
import com.mine.idea.repository.IdeaRepository;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @stefanl
 */
@RestController
@RequestMapping("/ideas")
public class IdeaController {


    private IdeaRepository ideaRepository;

    public IdeaController(IdeaRepository ideaRepository) {
        this.ideaRepository = ideaRepository;
    }

    @PostMapping
    public void addTask(@RequestBody Idea idea) {
        ideaRepository.save(idea);
    }

    @GetMapping
    public List<Idea> getIdeas() {
        return ideaRepository.findAll();
    }

    @PutMapping("/{id}")
    public void editIdea(@PathVariable long id, @RequestBody Idea idea) {
        Idea existingIdea = ideaRepository.findById(id).get();
        Assert.notNull(existingIdea, "Idea not found");
        existingIdea.setContent(idea.getContent());
        ideaRepository.save(existingIdea);
    }

    @DeleteMapping("/{id}")
    public void deleteIdea(@PathVariable long id) {
        Idea ideaToDel = ideaRepository.findById(id).get();
        ideaRepository.delete(ideaToDel);
    }
}
