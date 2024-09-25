package com.alex.eko.paragon.utils.rest.swagger;


import com.alex.eko.paragon.company.dto.CashRegisterDTO;
import com.alex.eko.paragon.utils.UtilName;
import com.alex.eko.paragon.utils.rest.doc.HttpCode;
import com.alex.eko.paragon.utils.exceptions.ExceptionBody;
import com.alex.eko.paragon.utils.rest.doc.ApiDoc;
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

@Tag(name = UtilName.CASH_REGISTER + UtilName.CONTROLLER)
public interface CashRegisterControllerDocs {


    @Operation(
            summary = ApiDoc.UPDATE_CREATE_SUMMARY,
            description = "You can create as many virtual cash registers as you want. ",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = ApiDoc.MEDIA_TYPE,
                            schema = @Schema(implementation = CashRegisterDTO.class)),
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
    void update(@RequestBody CashRegisterDTO dto);




    @ApiResponses({
            @ApiResponse(
                    responseCode = HttpCode.OK,
                    description = ApiDoc.SUCCESS_RETRIEVED_BY_ID_DESC,
                    content = @Content(
                            mediaType = ApiDoc.MEDIA_TYPE,
                            schema = @Schema(implementation = CashRegisterDTO.class)
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
    CashRegisterDTO getById(@PathVariable("id") Long id);




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
                            schema = @Schema(implementation = CashRegisterDTO.class)
                    )
            )
    })
    @GetMapping
    List<CashRegisterDTO> getAll(@RequestParam(required = false, defaultValue = "false") Boolean deleted);



}
