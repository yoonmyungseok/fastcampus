package com.fastcampus.programming.dmaker.controller;

import com.fastcampus.programming.dmaker.dto.CreateDeveloper;
import com.fastcampus.programming.dmaker.dto.DeveloperDto;
import com.fastcampus.programming.dmaker.entity.Developer;
import com.fastcampus.programming.dmaker.service.DMakerService;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@ToString
public class DMakerController {
  private final DMakerService dMakerService;

  @GetMapping("/developers")
  public List<DeveloperDto> getAllDevelopers() {
    // GET /developers HTTP/1.1
    log.info("GET /developers HTTP/1.1");

    return dMakerService.getAllDevelopers();
  }

  @PostMapping("/create-developer")
  public CreateDeveloper.Response createDevelopers(
      @Valid @RequestBody CreateDeveloper.Request request
      ) {
    log.info("request: {}", request);

    return dMakerService.createDeveloper(request);
  }
}
