package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Project {
    @SerializedName("title")
    String projectName;
    @SerializedName("code")
    String projectCode;
    String description;
    @SerializedName("access_type")
    String projectAccessType;
}