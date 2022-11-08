package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import tests.api.moduls.Project.Entity;

import java.util.ArrayList;

@AllArgsConstructor
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class TestRun {
    public int total;
    public int filtered;
    public int count;
    public ArrayList<Entity> entities;
}
