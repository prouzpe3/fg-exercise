package fg.exercise.apis;

import fg.exercise.models.dtos.EntityNotFoundDto;
import fg.exercise.models.dtos.GetTemperatureDto;
import fg.exercise.models.dtos.PostTemperatureDto;
import fg.exercise.models.dtos.PutTemperatureDto;
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
 * A delegate to be called by the {@link TemperaturesApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public interface TemperaturesApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * GET /temperatures : List all temperature records
     *
     * @return Successful Response (status code 200)
     * @see TemperaturesApi#temperaturesGet
     */
    default ResponseEntity<List<GetTemperatureDto>> temperaturesGet() throws Exception {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "null";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.valueOf(200));

    }

    /**
     * DELETE /temperatures/{id} : Remove given temperature
     *
     * @param id  (required)
     * @return Successful Response (status code 204)
     * @see TemperaturesApi#temperaturesIdDelete
     */
    default ResponseEntity<Void> temperaturesIdDelete(Long id) throws Exception {
        return new ResponseEntity<>(HttpStatus.OK);

    }

    /**
     * GET /temperatures/{id} : Retrieve given temperature
     *
     * @param id  (required)
     * @return Successful Response (status code 200)
     *         or Entity was not found. (status code 404)
     * @see TemperaturesApi#temperaturesIdGet
     */
    default ResponseEntity<GetTemperatureDto> temperaturesIdGet(Long id) throws Exception {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "null";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.valueOf(200));

    }

    /**
     * PUT /temperatures/{id} : Update given temperature
     *
     * @param id  (required)
     * @param putTemperatureDto Body describing the new values for the temperature record to be stored in the database. (required)
     * @return Successful Response (status code 201)
     * @see TemperaturesApi#temperaturesIdPut
     */
    default ResponseEntity<GetTemperatureDto> temperaturesIdPut(Long id,
        PutTemperatureDto putTemperatureDto) throws Exception {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "null";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.valueOf(201));

    }

    /**
     * POST /temperatures : Create a temperature record
     *
     * @param postTemperatureDto Body describing a temperature record to be stored in the database. (required)
     * @return Successful Response (status code 201)
     * @see TemperaturesApi#temperaturesPost
     */
    default ResponseEntity<GetTemperatureDto> temperaturesPost(PostTemperatureDto postTemperatureDto) throws Exception {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "null";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.valueOf(201));

    }

}
