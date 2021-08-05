/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (5.2.1-SNAPSHOT).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package fg.exercise.apis;

import fg.exercise.models.dtos.EntityNotFoundDto;
import fg.exercise.models.dtos.GetTemperatureDto;
import fg.exercise.models.dtos.PostTemperatureDto;
import fg.exercise.models.dtos.PutTemperatureDto;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
@Validated
@Api(value = "temperatures", description = "the temperatures API")
public interface TemperaturesApi {

    default TemperaturesApiDelegate getDelegate() {
        return new TemperaturesApiDelegate() {};
    }

    /**
     * GET /temperatures : List all temperature records
     *
     * @return Successful Response (status code 200)
     */
    @ApiOperation(value = "List all temperature records", nickname = "temperaturesGet", notes = "", response = GetTemperatureDto.class, responseContainer = "List", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successful Response", response = GetTemperatureDto.class, responseContainer = "List") })
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/temperatures",
        produces = "application/json"
    )
    default ResponseEntity<List<GetTemperatureDto>> temperaturesGet() throws Exception {
        return getDelegate().temperaturesGet();
    }


    /**
     * DELETE /temperatures/{id} : Remove given temperature
     *
     * @param id  (required)
     * @return Successful Response (status code 204)
     */
    @ApiOperation(value = "Remove given temperature", nickname = "temperaturesIdDelete", notes = "", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "Successful Response") })
    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/temperatures/{id}"
    )
    default ResponseEntity<Void> temperaturesIdDelete(@ApiParam(value = "",required=true) @PathVariable("id") Long id) throws Exception {
        return getDelegate().temperaturesIdDelete(id);
    }


    /**
     * GET /temperatures/{id} : Retrieve given temperature
     *
     * @param id  (required)
     * @return Successful Response (status code 200)
     *         or Entity was not found. (status code 404)
     */
    @ApiOperation(value = "Retrieve given temperature", nickname = "temperaturesIdGet", notes = "", response = GetTemperatureDto.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successful Response", response = GetTemperatureDto.class),
        @ApiResponse(code = 404, message = "Entity was not found.", response = EntityNotFoundDto.class) })
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/temperatures/{id}",
        produces = "application/json"
    )
    default ResponseEntity<GetTemperatureDto> temperaturesIdGet(@ApiParam(value = "",required=true) @PathVariable("id") Long id) throws Exception {
        return getDelegate().temperaturesIdGet(id);
    }


    /**
     * PUT /temperatures/{id} : Update given temperature
     *
     * @param id  (required)
     * @param putTemperatureDto Body describing the new values for the temperature record to be stored in the database. (required)
     * @return Successful Response (status code 200)
     *         or Entity was not found. (status code 404)
     */
    @ApiOperation(value = "Update given temperature", nickname = "temperaturesIdPut", notes = "", response = GetTemperatureDto.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successful Response", response = GetTemperatureDto.class),
        @ApiResponse(code = 404, message = "Entity was not found.", response = EntityNotFoundDto.class) })
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/temperatures/{id}",
        produces = "application/json",
        consumes = "application/json"
    )
    default ResponseEntity<GetTemperatureDto> temperaturesIdPut(@ApiParam(value = "",required=true) @PathVariable("id") Long id,@ApiParam(value = "Body describing the new values for the temperature record to be stored in the database." ,required=true )  @Valid @RequestBody PutTemperatureDto putTemperatureDto) throws Exception {
        return getDelegate().temperaturesIdPut(id, putTemperatureDto);
    }


    /**
     * POST /temperatures : Create a temperature record
     *
     * @param postTemperatureDto Body describing a temperature record to be stored in the database. (required)
     * @return Successful Response (status code 201)
     */
    @ApiOperation(value = "Create a temperature record", nickname = "temperaturesPost", notes = "", response = GetTemperatureDto.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Successful Response", response = GetTemperatureDto.class) })
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/temperatures",
        produces = "application/json",
        consumes = "application/json"
    )
    default ResponseEntity<GetTemperatureDto> temperaturesPost(@ApiParam(value = "Body describing a temperature record to be stored in the database." ,required=true )  @Valid @RequestBody PostTemperatureDto postTemperatureDto) throws Exception {
        return getDelegate().temperaturesPost(postTemperatureDto);
    }

}
