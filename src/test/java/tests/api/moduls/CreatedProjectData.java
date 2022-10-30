package tests.api.moduls;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CreatedProjectData {
    String title;
    String code;
    @Expose(serialize = false, deserialize = false)
    String access_type;
}