package fg.exercise.apis;

import fg.exercise.models.dtos.GetTemperatureRangeDto;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * A delegate to be called by the {@link TemperatureRangeApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public interface TemperatureRangeApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * GET /temperature-range : Finds the longest range the temperature was inside the bounds considering the optional daytime bounds.
     *
     * @param tempLBound The lower bound for the temperature. (required)
     * @param tempUBound The upper bound for the temperature. (required)
     * @param timeLBound The lower bound for the time of the day. If not specified midnight is used. (optional)
     * @param timeUBound The upper bound for the time of the day. If not specified midnight is used. (optional)
     * @return Successful Response (status code 200)
     * @see TemperatureRangeApi#temperatureRangeGet
     */
    default ResponseEntity<GetTemperatureRangeDto> temperatureRangeGet(Double tempLBound,
        Double tempUBound,
        Integer timeLBound,
        Integer timeUBound) throws Exception {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"timestampFrom\" : \"2021-08-01 12:59:59.00\", \"timestampTo\" : \"2021-08-03 12:59:59.00\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
