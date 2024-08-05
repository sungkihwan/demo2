package com.example.demo2;

import com.example.demo2.dto.Request1;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/test")
public class Controller {
    private final TestService service;

    public record Request2(
            @NotBlank(message = "test: 필수")
            @Schema(description = "사용자 이메일", example = "testtest@gmail.com")
            String test,
            @Min(value = 1, message = "page: 페이지 번호는 1 이상이어야 합니다.") Integer t2
    ) {
    }

    @GetMapping()
    @Operation(summary = "TEST", description = "TEST")
    public Object test(
            @Valid Request1 query,
            @Valid @RequestBody Request1 body
    ) {
        System.out.println(body);
        return query;
    }

    @DeleteMapping("/{id:[0-9]+}/{ssss}")
    public String test2(
            @PathVariable Long id,
            @PathVariable @Size(max = 10) String ssss
    ) {
        System.out.println(id);
        return ssss;
    }

    @PatchMapping("/{id:[0-9]+}")
    public Request2 test3(
            @PathVariable Long id,
            @RequestBody @Valid Request2 t
    ) {
        return t;
    }

    @GetMapping("/page")
    public Page page(
            Pageable pageable
    ) {
        log.debug(pageable);
        return service.test(pageable);
    }

    @GetMapping("/page2")
    public Page page2(
            Pageable pageable
    ) {
        return service.test2(pageable);
    }

    @GetMapping("/page3")
    public Page page3(
            Pageable pageable
    ) {
        log.debug(pageable);
        return service.test3(pageable);
    }

    @GetMapping("/page4")
    public Page page4(
            Pageable pageable
    ) {
        log.debug(pageable);
        return service.test4(pageable);
    }

    @GetMapping("/page5")
    public Page page5(
            Pageable pageable
    ) {
        log.debug(pageable);
        return service.test5(pageable);
    }

    @GetMapping("/page6")
    public Page page6(
            Pageable pageable
    ) {
        log.debug(pageable);
        return service.test6(pageable);
    }

    @GetMapping("/page7")
    public List page7(
            Pageable pageable
    ) {
        return service.test7(pageable);
    }

    @GetMapping("/page8")
    public TestService.CustomPage page8(
            Pageable pageable
    ) {
        return service.test8(pageable);
    }

    @GetMapping("/page9")
    public List page9(
            Pageable pageable
    ) {
        return service.test9(pageable);
    }
}

