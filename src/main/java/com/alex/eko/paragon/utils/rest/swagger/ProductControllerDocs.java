package com.alex.eko.paragon.utils.rest.swagger;


import com.alex.eko.paragon.receipt.dto.ProductDTO;
import com.alex.eko.paragon.utils.UtilName;
import com.alex.eko.paragon.utils.rest.doc.ApiDoc;
import com.alex.eko.paragon.utils.rest.doc.HttpCode;
import com.alex.eko.paragon.utils.exceptions.ExceptionBody;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = UtilName.PRODUCT + UtilName.CONTROLLER)
public interface ProductControllerDocs {


    @Operation(
            summary = ApiDoc.UPDATE_CREATE_SUMMARY,
            description = ApiDoc.UPDATE_CREATE_DESCRIPTION,
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = ApiDoc.MEDIA_TYPE,
                            schema = @Schema(implementation = ProductDTO.class)),
                    required = true))
    @ApiResponses({
            @ApiResponse(
                    responseCode = HttpCode.OK,
                    description = ApiDoc.SUCCESS_CREATE_UPDATE_DESC,
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = HttpCode.NOT_FOUND,
                    description = ApiDoc.ENTITY_NOT_FOUND_DESC,
                    content = @Content(
                            mediaType = ApiDoc.MEDIA_TYPE,
                            schema = @Schema(implementation = ExceptionBody.class)
                    )
            )
    })
    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    void update(@RequestBody ProductDTO dto);


    @ApiResponses({
            @ApiResponse(
                    responseCode = HttpCode.OK,
                    description = ApiDoc.SUCCESS_RETRIEVED_BY_ID_DESC,
                    content = @Content(
                            mediaType = ApiDoc.MEDIA_TYPE,
                            schema = @Schema(implementation = ProductDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = HttpCode.NOT_FOUND,
                    description = ApiDoc.ENTITY_NOT_FOUND_DESC,
                    content = @Content(
                            mediaType = ApiDoc.MEDIA_TYPE,
                            schema = @Schema(implementation = ExceptionBody.class)
                    )
            )
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    ProductDTO getById(@PathVariable("id") String id);


    @Operation(
            summary = ApiDoc.GET_ALL_SUMMARY,
            description = ApiDoc.GET_ALL_DESCRIPTION,
            parameters = {
                    @Parameter(
                            name = ApiDoc.PARAM_DELETED,
                            description = ApiDoc.GET_ALL_PARAMETER_DESCRIPTION,
                            in = ParameterIn.QUERY,
                            example = "false",
                            schema = @Schema(
                                    type = "boolean",
                                    allowableValues = {"true", "false"},
                                    defaultValue = "false"
                            )
                    )
            }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = HttpCode.OK,
                    description = ApiDoc.SUCCESS_RETRIEVED_BY_ID_DESC,
                    content = @Content(
                            mediaType = ApiDoc.MEDIA_TYPE,
                            schema = @Schema(implementation = ProductDTO.class)
                    )
            )
    })
    @GetMapping
    List<ProductDTO> getAll(@RequestParam(required = false, defaultValue = "false") Boolean deleted);


}
