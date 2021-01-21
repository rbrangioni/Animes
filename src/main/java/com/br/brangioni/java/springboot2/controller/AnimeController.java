package com.br.brangioni.java.springboot2.controller;

import com.br.brangioni.java.springboot2.domain.Anime;
import com.br.brangioni.java.springboot2.request.AnimePostRequestBody;
import com.br.brangioni.java.springboot2.request.AnimePutRequestBody;
import com.br.brangioni.java.springboot2.service.AnimeService;
import com.br.brangioni.java.springboot2.util.DataUtil;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("animes")
@Log4j2
@AllArgsConstructor
public class AnimeController {
    private DataUtil dataUtil;
    private final AnimeService animeService;

//    @GetMapping
//    public ResponseEntity<List<Anime>> list() {
//        log.info(dataUtil.formatLocalDateTimeToDataBaseStyle(LocalDateTime.now()));
//        //System.out.println("Data Formatada: "+dataUtil.formatLocalDateTimeToDataBaseStyle(LocalDateTime.now()));
//        return ResponseEntity.ok(animeService.listAll());
//    }

    @GetMapping
    public ResponseEntity<Page<Anime>> list(Pageable pageable) {
        //log.info(dataUtil.formatLocalDateTimeToDataBaseStyle(LocalDateTime.now()));
        //System.out.println("Data Formatada: "+dataUtil.formatLocalDateTimeToDataBaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(animeService.listAll(pageable));
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Anime>> listAll() {
        //log.info(dataUtil.formatLocalDateTimeToDataBaseStyle(LocalDateTime.now()));
        //System.out.println("Data Formatada: "+dataUtil.formatLocalDateTimeToDataBaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(animeService.listAllNoPageable());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Anime> findById(@PathVariable long id) {

        return ResponseEntity.ok(animeService.findByIdOrTrowbadRequestException(id));
    }

    @GetMapping(path = "/find")
    public ResponseEntity<List<Anime>> findByName(@RequestParam String name) {
        return ResponseEntity.ok(animeService.findByName(name));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Anime> save(@RequestBody @Valid AnimePostRequestBody animePostRequestBody) {
        return new ResponseEntity<>(animeService.save(animePostRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        animeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody AnimePutRequestBody animePutRequestBody) {
        animeService.replace(animePutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
