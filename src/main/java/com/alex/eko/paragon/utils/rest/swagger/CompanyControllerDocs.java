package com.alex.eko.paragon.utils.rest.swagger;

import com.alex.eko.paragon.company.dto.CompanyDTO;
import com.alex.eko.paragon.utils.UtilName;
import com.alex.eko.paragon.utils.rest.doc.HttpCode;
import com.alex.eko.paragon.utils.exceptions.ExceptionBody;
import com.alex.eko.paragon.utils.rest.doc.ApiDoc;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = UtilName.COMPANY + UtilName.CONTROLLER)
public interface CompanyControllerDocs {


    @Operation(
            summary = ApiDoc.UPDATE_CREATE_SUMMARY,
            description = ApiDoc.UPDATE_CREATE_DESCRIPTION,
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = ApiDoc.MEDIA_TYPE,
                            schema = @Schema(implementation = CompanyDTO.class)),
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
    void update(@RequestBody CompanyDTO dto);


    @ApiResponses({
            @ApiResponse(
                    responseCode = HttpCode.OK,
                    description = ApiDoc.SUCCESS_RETRIEVED_BY_ID_DESC,
                    content = @Content(
                            mediaType = ApiDoc.MEDIA_TYPE,
                            schema = @Schema(implementation = CompanyDTO.class)
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
    @GetMapping
    CompanyDTO getMyCompany();




    @ApiResponses({
            @ApiResponse(
                    responseCode = HttpCode.OK,
                    description = ApiDoc.SUCCESS_RETRIEVED_BY_ID_DESC,
                    content = @Content(
                            mediaType = ApiDoc.MEDIA_TYPE,
                            schema = @Schema(implementation = CompanyDTO.class)
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
    CompanyDTO getById(@PathVariable("id") Long id);




}
