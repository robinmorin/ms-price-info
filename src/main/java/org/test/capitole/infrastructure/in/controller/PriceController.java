package org.test.capitole.infrastructure.in.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.test.capitole.domain.ports.in.SearchPriceInputPort;
import org.test.capitole.infrastructure.in.dto.PriceResponse;
import org.test.capitole.infrastructure.in.exception.RecordNotFoundException;
import org.test.capitole.infrastructure.in.exception.RestExceptionHandler;
import org.test.capitole.infrastructure.in.mapper.PriceResponseMapper;

import java.time.LocalDateTime;

@Slf4j
@Tag(name = "Prices", description = "Controller for Prices Product Operations")
@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class PriceController {

    private final SearchPriceInputPort searchPriceInputPort;
    private final PriceResponseMapper priceResponseMapper;

    @Operation(summary = "Request price product by brand that could be apply in specific date")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PriceResponse.class))),
        @ApiResponse(responseCode = "400", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RestExceptionHandler.Error.class))),
        @ApiResponse(responseCode = "422", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RestExceptionHandler.Error.class))),
        @ApiResponse(responseCode = "500", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = RestExceptionHandler.Error.class))),
        }
    )
    @Parameter(name = "productId", description = "Product Id")
    @Parameter(name = "brandId", description = "Brand Id")
    @Parameter(name = "effectiveDate", description = "Date for apply requesting", example = "2024-03-01 15:00:00")
    @GetMapping("/{productId}/brand/{brandId}/price-to-apply")
    ResponseEntity<PriceResponse> getPriceToApply(@PathVariable
                                                  @NotNull(message = "ProductId must have a valid value")
                                                  @Min(value = 1, message = "ProductId must be greater than 0")
                                                  Long productId,
                                                  @PathVariable
                                                  @NotNull(message = "BrandId must have a valid value")
                                                  @Min(value = 1, message = "BrandId must be greater than 0")
                                                  Integer brandId,
                                                  @RequestParam
                                                  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = DateTimeFormat.ISO.DATE_TIME)
                                                  LocalDateTime effectiveDate){

        log.info("Requesting price for Product: {} Brand: {} EffectiveDate: {}", productId, brandId, effectiveDate);
        var response = searchPriceInputPort.searchByMostPriority(productId, brandId, effectiveDate)
                                   .map(priceResponseMapper::toResponse)
                                   .orElseThrow(()-> new RecordNotFoundException("Price not found with the given parameters"));
        log.info("Returning price found - Price List: {}, Value: {}, EffectiveDateRange: {}", response.getPriceListId(), response.getPriceToApply(), response.getEffectiveDateRange());
        return ResponseEntity.ok(response);
    }

}
