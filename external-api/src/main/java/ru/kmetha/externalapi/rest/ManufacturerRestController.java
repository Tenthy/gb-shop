package ru.kmetha.externalapi.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.kmetha.gbapi.manufacturer.api.ManufacturerGateway;
import ru.kmetha.gbapi.manufacturer.dto.ManufacturerDto;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/manufacturer")
public class ManufacturerRestController {

    @Autowired
    private final ManufacturerGateway manufacturerGateway;

    @GetMapping
    public List<ManufacturerDto> getManufacturerList() {
        return manufacturerGateway.getManufacturerList();
    }

    @GetMapping("/{manufacturerId}")
    public ResponseEntity<?> getManufacturer(@PathVariable("manufacturerId") Long id) {
        return manufacturerGateway.getManufacturer(id);
    }

    @PostMapping
    public ResponseEntity<?> handlePost(@Validated @RequestBody ManufacturerDto manufacturerDto) {
        return manufacturerGateway.handlePost(manufacturerDto);
    }

    @PutMapping("/{manufacturerId}")
    public ResponseEntity<?> handleUpdate(@PathVariable("manufacturerId") Long id,
                                          @Validated @RequestBody ManufacturerDto manufacturerDto) {
        return manufacturerGateway.handleUpdate(id, manufacturerDto);

    }

    @DeleteMapping("/{manufacturerId}")
    public void deleteById(@PathVariable("manufacturerId") Long id) {
        manufacturerGateway.deleteById(id);
    }
}
