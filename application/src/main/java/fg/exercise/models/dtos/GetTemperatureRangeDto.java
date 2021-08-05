package fg.exercise.models.dtos;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;

/**
 * GetTemperatureRangeDto
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class GetTemperatureRangeDto  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("timestampFrom")
  private String timestampFrom;

  @JsonProperty("timestampTo")
  private String timestampTo;

  public GetTemperatureRangeDto timestampFrom(String timestampFrom) {
    this.timestampFrom = timestampFrom;
    return this;
  }

  /**
   * Get timestampFrom
   * @return timestampFrom
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getTimestampFrom() {
    return timestampFrom;
  }

  public void setTimestampFrom(String timestampFrom) {
    this.timestampFrom = timestampFrom;
  }

  public GetTemperatureRangeDto timestampTo(String timestampTo) {
    this.timestampTo = timestampTo;
    return this;
  }

  /**
   * Get timestampTo
   * @return timestampTo
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getTimestampTo() {
    return timestampTo;
  }

  public void setTimestampTo(String timestampTo) {
    this.timestampTo = timestampTo;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetTemperatureRangeDto getTemperatureRange = (GetTemperatureRangeDto) o;
    return Objects.equals(this.timestampFrom, getTemperatureRange.timestampFrom) &&
        Objects.equals(this.timestampTo, getTemperatureRange.timestampTo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(timestampFrom, timestampTo);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetTemperatureRangeDto {\n");
    
    sb.append("    timestampFrom: ").append(toIndentedString(timestampFrom)).append("\n");
    sb.append("    timestampTo: ").append(toIndentedString(timestampTo)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

