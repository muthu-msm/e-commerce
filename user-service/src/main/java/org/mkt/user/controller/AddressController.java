package org.mkt.user.controller;


import lombok.RequiredArgsConstructor;
import org.mkt.common.dto.AddressRequest;
import org.mkt.common.dto.AddressResponse;
import org.mkt.common.dto.Response;
import org.mkt.user.service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @PostMapping
    public ResponseEntity<Response<AddressResponse>> create(@RequestBody AddressRequest request) {
        return ResponseEntity.ok(addressService.create(request));
    }

    @PutMapping
    public ResponseEntity<Response<AddressResponse>> update(@RequestBody AddressRequest request) {
        return ResponseEntity.ok(addressService.update(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<AddressResponse>> create(@PathVariable Long id) {
        return ResponseEntity.ok(addressService.get(id));
    }

}
