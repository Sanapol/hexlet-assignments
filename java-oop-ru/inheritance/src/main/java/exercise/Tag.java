package exercise;

import java.util.stream.Collectors;
import java.util.Map;

// BEGIN
public class Tag {
    public String tag;
    public Map<String, String> tags;

    public Tag(String tag, Map<String, String> tags) {
        this.tag = tag;
        this.tags = tags;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public String getTag() {
        return tag;
    }

    public String mapToString(Map<String, String> mapToString) {
        var maps = mapToString.entrySet();
        String mapString = "";
        for (var map : maps) {
            mapString += " " + map.getKey() + "=\"" + map.getValue() + "\"";
        }
        return mapString;
    }
}
// END
