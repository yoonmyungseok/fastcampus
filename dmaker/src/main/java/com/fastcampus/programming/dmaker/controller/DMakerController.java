package com.fastcampus.programming.dmaker.controller;

import com.fastcampus.programming.dmaker.dto.CreateDeveloper;
import com.fastcampus.programming.dmaker.dto.DeveloperDetailDto;
import com.fastcampus.programming.dmaker.dto.DeveloperDto;
import com.fastcampus.programming.dmaker.dto.EditDeveloper;
import com.fastcampus.programming.dmaker.service.DMakerService;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    return dMakerService.getAllEmployedDevelopers();
  }
  @GetMapping("/developer/{memberId}")
  public DeveloperDetailDto getDeveloperDetail(@PathVariable String memberId) {
    // GET /developers HTTP/1.1
    log.info("GET /developers HTTP/1.1");

    return dMakerService.getDeveloperDetail(memberId);
  }

  @PostMapping("/create-developer")
  public CreateDeveloper.Response createDevelopers(
      @Valid @RequestBody CreateDeveloper.Request request
      ) {
    log.info("request: {}", request);

    return dMakerService.createDeveloper(request);
  }

  @PutMapping("/developer/{memberId}")
  public DeveloperDetailDto editDeveloper(
      @PathVariable String memberId,
      @Valid @RequestBody EditDeveloper.Request request
      ) {
    log.info("GET /developers HTTP/1.1");

    return dMakerService.editDeveloper(memberId,request);
  }

  @DeleteMapping("/developer/{memberId}")
  public DeveloperDetailDto deleteDeveloper(@PathVariable String memberId){
    return dMakerService.deleteDeveloper(memberId);
  }
}
