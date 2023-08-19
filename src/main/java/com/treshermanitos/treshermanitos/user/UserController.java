package com.treshermanitos.treshermanitos.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.treshermanitos.treshermanitos.config.ApiResponse;
import com.treshermanitos.treshermanitos.exceptions.UserNotFoundException;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAll() {
        try {
            return ApiResponse.oK(userService.getAll());
        } catch (UserNotFoundException e) {
            return ApiResponse.notFound(e.getMessage());
        } catch (Exception e) {
            return ApiResponse.serverError();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getById(@PathVariable(value = "id") long id) {
        try {
            return ApiResponse.oK(userService.getById(id));
        } catch (UserNotFoundException e) {
            return ApiResponse.notFound(e.getMessage());
        } catch (Exception e) {
            return ApiResponse.serverError();
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable(value = "id") long id, @RequestBody UserDTO body) {
        try {
            return ApiResponse.oK(userService.updateById(id, body));
        } catch (UserNotFoundException e) {
            return ApiResponse.notFound(e.getMessage() != null ? e.getMessage() : "Uknown");
        } catch (Exception e) {
            return ApiResponse.serverError();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable(value = "id") long id){
        try {
            return ApiResponse.oK(userService.deleteById(id));
        } catch (UserNotFoundException e) {
            return ApiResponse.notFound(e.getMessage());
        } catch (Exception e) {
            return ApiResponse.serverError();
        }
    }

}
