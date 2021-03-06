/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (5.2.1-SNAPSHOT).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package fg.exercise.apis;

import fg.exercise.models.dtos.GetTemperatureRangeDto;
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
@Api(value = "temperature-range", description = "the temperature-range API")
public interface TemperatureRangeApi {

    default TemperatureRangeApiDelegate getDelegate() {
        return new TemperatureRangeApiDelegate() {};
    }

    /**
     * GET /temperature-range : Finds the longest range the temperature was inside the bounds considering the optional daytime bounds.
     *
     * @param tempLBound The lower bound for the temperature. (required)
     * @param tempUBound The upper bound for the temperature. (required)
     * @param timeLBound The lower bound for the time of the day. If not specified midnight is used. (optional)
     * @param timeUBound The upper bound for the time of the day. If not specified midnight is used. (optional)
     * @return Successful Response (status code 200)
     */
    @ApiOperation(value = "Finds the longest range the temperature was inside the bounds considering the optional daytime bounds.", nickname = "temperatureRangeGet", notes = "", response = GetTemperatureRangeDto.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successful Response", response = GetTemperatureRangeDto.class) })
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/temperature-range",
        produces = "application/json"
    )
    default ResponseEntity<GetTemperatureRangeDto> temperatureRangeGet(@NotNull @ApiParam(value = "The lower bound for the temperature.", required = true) @Valid @RequestParam(value = "tempLBound", required = true) Double tempLBound,@NotNull @ApiParam(value = "The upper bound for the temperature.", required = true) @Valid @RequestParam(value = "tempUBound", required = true) Double tempUBound,@Min(0) @Max(24) @ApiParam(value = "The lower bound for the time of the day. If not specified midnight is used.") @Valid @RequestParam(value = "timeLBound", required = false) Integer timeLBound,@Min(0) @Max(24) @ApiParam(value = "The upper bound for the time of the day. If not specified midnight is used.") @Valid @RequestParam(value = "timeUBound", required = false) Integer timeUBound) throws Exception {
        return getDelegate().temperatureRangeGet(tempLBound, tempUBound, timeLBound, timeUBound);
    }

}
