package com.example.demo2;

import com.example.demo2.repository.Repository;
import com.example.demo2.repository.CustomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class TestService {
    private final Repository repository;
    private final CustomRepository customRepository;

    public Page test(Pageable pageable) {
        return customRepository.find(pageable);
    }

    public Page test2(Pageable pageable) {
        return customRepository.find2(pageable);
    }

    public Page test3(Pageable pageable) {
        return customRepository.find3(pageable);
    }
    public Page test4(Pageable pageable) {
        return customRepository.find4(pageable);
    }

    public Page test5(Pageable pageable) {
        return customRepository.find5(pageable);
    }

    public Page test6(Pageable pageable) {
        return customRepository.find6(pageable);
    }

    public List test7(Pageable pageable) {
        return customRepository.find7(pageable);
    }

    public record CustomPage<T>(T data, int size, int page, int totalPage, String sort) {}

    public CustomPage test8(Pageable pageable) {
        Page<CustomRepository.Response3> result =  customRepository.find8(pageable);
        return new CustomPage<List<CustomRepository.Response3>>(
                result.getContent(),
                pageable.getPageSize(),
                pageable.getPageNumber(),
                result.getTotalPages(),
                pageable.getSort().toString()
        );
    }

    public List test9(Pageable pageable) {
        return customRepository.find9(pageable);
    }
}
