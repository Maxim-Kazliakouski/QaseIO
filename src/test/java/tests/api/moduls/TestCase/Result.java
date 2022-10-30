package tests.api.moduls.TestCase;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {
    public int id;
    public int position;
    public String title;
    public String description;
        public Object preconditions;
        public Object postconditions;
        public int severity;
        public int priority;
        public int type;
        public int layer;
        public int is_flaky;
        public int behavior;
        public int automation;
        public int status;
        public Object milestone_id;
        public int suite_id;
        public ArrayList<Object> links;
        public ArrayList<Object> custom_fields;
        public ArrayList<Object> attachments;
        public String steps_type;
        public ArrayList<Step> steps;
        public ArrayList<Object> params;
        public int member_id;
        public int project_id;
        public ArrayList<Object> tags;
        public Object deleted;
        public Date created;
        public Date updated;
        public Date created_at;
        public Date updated_at;
}