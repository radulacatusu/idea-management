package com.mine.idea.controller;

import com.mine.idea.api.IdeaApi;
import com.mine.idea.model.Idea;
import com.mine.idea.model.User;
import com.mine.idea.repository.IdeaRepository;
import com.mine.idea.repository.UserRepository;
import com.mine.idea.security.JwtConfig;
import com.mine.idea.security.JwtTokenProvider;
import com.mine.idea.util.IdeaConverter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @stefanl
 */
@RestController
@RequestMapping("/ideas")
public class IdeaController {
    @Autowired
    private IdeaRepository ideaRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private IdeaConverter ideaConverter;

    @Autowired
    private JwtConfig jwtConfig;

    @PostMapping
    public ResponseEntity<IdeaApi> addIdea(@Valid @RequestBody IdeaApi ideaApi, HttpServletRequest req) {
        if (StringUtils.isBlank(ideaApi.getContent()) || (ideaApi.getImpact() > 10 || ideaApi.getImpact() < 0)
                || (ideaApi.getConfidence() > 10 || ideaApi.getConfidence() < 0)
                || (ideaApi.getEase() > 10 || ideaApi.getEase() < 0)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Idea idea = ideaConverter.fromDTO(ideaApi);
        idea.setCreatedAt(new Date());
        idea.setUser(getUser(req));
        idea.setAverageScore((double) (idea.getConfidence() + idea.getEase() + idea.getImpact()) / 3);
        idea = ideaRepository.save(idea);

        IdeaApi response = ideaConverter.toDTO(idea);
        return new ResponseEntity<>(response, null, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<IdeaApi>> getIdeas(HttpServletRequest req) {
        User user = getUser(req);
        return new ResponseEntity<>(ideaRepository.findByUser(user, PageRequest.of(0, 10)).stream()
                .map(ideaConverter::toDTO).collect(Collectors.toList()), null, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IdeaApi> editIdea(@PathVariable long id, @RequestBody IdeaApi idea) {
        if (StringUtils.isBlank(idea.getContent()) || (idea.getImpact() > 10 || idea.getImpact() < 0)
                || (idea.getConfidence() > 10 || idea.getConfidence() < 0)
                || (idea.getEase() > 10 || idea.getEase() < 0)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Optional<Idea> existingIdeaOpt = ideaRepository.findById(id);
        if (existingIdeaOpt.isPresent()) {
            Idea existingIdea = existingIdeaOpt.get();
            existingIdea.setContent(idea.getContent());
            existingIdea.setConfidence(idea.getConfidence());
            existingIdea.setEase(idea.getEase());
            existingIdea.setImpact(idea.getImpact());
            existingIdea.setAverageScore((double) (existingIdea.getConfidence() + existingIdea.getEase() + existingIdea.getImpact()) / 3);
            ideaRepository.save(existingIdea);
            return new ResponseEntity<>(ideaConverter.toDTO(existingIdea), null, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteIdea(@PathVariable long id) {
        Optional<Idea> idea = ideaRepository.findById(id);

        if (idea.isPresent()) {
            ideaRepository.delete(idea.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    private User getUser(HttpServletRequest req) {
        String header = req.getHeader(jwtConfig.getHeader());
        User applicationUser = userRepository.findByEmail(jwtTokenProvider.getUsername(header));
        return applicationUser;
    }
}
