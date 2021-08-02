package fg.exercise.apis;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
@Controller
@RequestMapping("${openapi.FG-Exercise.base-path:/p6331/FG-Exercise/1.0.0}")
public class TemperatureRangeApiController implements TemperatureRangeApi {

    private final TemperatureRangeApiDelegate delegate;

    public TemperatureRangeApiController(@org.springframework.beans.factory.annotation.Autowired(required = false) TemperatureRangeApiDelegate delegate) {
        this.delegate = Optional.ofNullable(delegate).orElse(new TemperatureRangeApiDelegate() {});
    }

    @Override
    public TemperatureRangeApiDelegate getDelegate() {
        return delegate;
    }

}
