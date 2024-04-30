package dev.iury.project.controllers;
import java.util.List;
import java.util.Optional;
import dev.iury.project.dataVO.PersonVO;
import dev.iury.project.dataVO2.PersonVOV2;
import dev.iury.project.service.PersonService;
import dev.iury.project.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static dev.iury.project.util.MediaType.APPLICATION_YML;

@RestController
@RequestMapping("api/person/v1")
@Tag(name = "People", description = "Endpoint for people management")
public class PersonController {

    @Autowired
    private PersonService service;

    @GetMapping(produces = {MediaType.APPLICATION_JSON,
                            MediaType.APPLICATION_XML,
                            MediaType.APPLICATION_YML})
    @Operation(summary = "List all people",
                description = "List all people from database",
                tags = {"People"},
                responses = {
                        @ApiResponse(
                            responseCode = "200",
                                description = "Success",
                                    content = {@Content(mediaType = MediaType.APPLICATION_JSON,
                                                        array = @ArraySchema(schema = @Schema(implementation = PersonVO.class)))
                                    }),
                        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                        @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
                        @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)
                }
    )
    public List<PersonVO> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/{id}",
            produces = {MediaType.APPLICATION_JSON,
                        MediaType.APPLICATION_XML,
                        MediaType.APPLICATION_YML})
    @Operation(summary = "List a person by id",
            description = "List a person by id from database",
            tags = {"People"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success",
                            content = @Content(schema = @Schema(implementation = PersonVO.class))),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                    @ApiResponse(responseCode = "204", description = "No Content", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)
            }
    )
    public PersonVO findById(@PathVariable(value = "id") Long id) {
        return service.findById(id);
    }


    @PostMapping(consumes = {MediaType.APPLICATION_JSON,
                            MediaType.APPLICATION_XML,
                            MediaType.APPLICATION_YML},

            produces = {MediaType.APPLICATION_JSON,
                        MediaType.APPLICATION_XML,
                        MediaType.APPLICATION_YML})
    @Operation(summary = "Create a person",
            description = "Create a person and insert into database",
            tags = {"People"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success",
                            content = @Content(schema = @Schema(implementation = PersonVO.class))),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)
            }
    )
    public PersonVO create(@RequestBody PersonVO person) {
        return service.create(person);
    }


    @PostMapping(value = "/v2",
            consumes = {MediaType.APPLICATION_JSON,
                        MediaType.APPLICATION_XML,
                        MediaType.APPLICATION_YML},

            produces = {MediaType.APPLICATION_JSON,
                        MediaType.APPLICATION_XML,
                        MediaType.APPLICATION_YML})
    public PersonVOV2 createv2(@RequestBody PersonVOV2 person) {
        return service.createv2(person);
    }

    @PutMapping(value = "/{id}",
            consumes = {MediaType.APPLICATION_JSON,
                        MediaType.APPLICATION_XML,
                        MediaType.APPLICATION_YML},

            produces = {MediaType.APPLICATION_JSON,
                        MediaType.APPLICATION_XML,
                        MediaType.APPLICATION_YML})
    @Operation(summary = "Updates a person",
            description = "Updates a person and insert into database",
            tags = {"People"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success",
                            content = @Content(schema = @Schema(implementation = PersonVO.class))),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)
            }
    )
    public PersonVO update(@RequestBody PersonVO person, @PathVariable Long id){
        return service.update(person, id);
    }

    @Operation(summary = "Deletes a person",
            description = "Deletes a person and insert into database",
            tags = {"People"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success",
                            content = @Content),

                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content),
                    @ApiResponse(responseCode = "204", description = "No Content", content = @Content),
            }
    )
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
