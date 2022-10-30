package tests.api.moduls.TestRun;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Date;

public class Entity {
        public int id;
        public String title;
        public Object description;
        public int status;
        public String status_text;
        public Date start_time;
        public Object end_time;
        @JsonProperty("public")
        public boolean mypublic;
        public Stats stats;
        public int time_spent;
        public int user_id;
        public Object environment;
        public Object milestone;
        public ArrayList<Object> custom_fields;
        public ArrayList<Object> tags;
}