package tests.api.moduls.TestCase;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Step {
    public String hash;
    public int position;
    public Object shared_step_hash;
    public Object shared_step_nested_hash;
    public ArrayList<Object> attachments;
    public String action;
    public String expected_result;
    public Object data;
}