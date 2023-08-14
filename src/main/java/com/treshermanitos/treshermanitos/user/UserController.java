package com.treshermanitos.treshermanitos.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.treshermanitos.treshermanitos.config.ApiResponse;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAll() {
        try {
            return ApiResponse.oK(userService.getAll());
        } catch (Exception e) {
            return ApiResponse.notFound(e.getMessage() != null ? e.getMessage() : "Uknown");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getById(@PathVariable(value = "id") long id) {
        try {
            return ApiResponse.oK(userService.getById(id));
        } catch (Exception e) {
            return ApiResponse.notFound(e.getMessage() != null ? e.getMessage() : "Uknown");
        }
    }

    /*
     * @GetMapping("/name/{name}")
     * public Country getCountryName(@PathVariable(value = "name") String name) {
     * return countryService.getName(name);
     * }
     */

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateCountry(@PathVariable(value = "id") long id, @RequestBody UserDTO body) {
        try {
            return ApiResponse.oK(userService.updateById(id, body));
        } catch (Exception e) {
            return ApiResponse.notFound(e.getMessage() != null ? e.getMessage() : "Uknown");
        }

    }

}
