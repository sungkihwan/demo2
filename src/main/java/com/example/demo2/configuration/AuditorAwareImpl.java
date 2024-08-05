//package com.example.demo2.configuration;
//
//import org.springframework.data.domain.AuditorAware;
//
//import java.util.Optional;
//
//public class AuditorAwareImpl implements AuditorAware<String> {
//
//    @Override
//    public Optional<String> getCurrentAuditor() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        if (authentication == null || !authentication.isAuthenticated()) {
//            return Optional.of("");
//        }
//
//        Object principal = authentication.getPrincipal();
//
//        if (principal instanceof CustomUserDetails) {
//            CustomUserDetails userDetails = (CustomUserDetails) principal;
//            return Optional.of(userDetails.getUsername());
//        } else if (principal instanceof String) {
//            return Optional.of((String) principal);
//        }
//
//        return Optional.of("");
//    }
//}
//
