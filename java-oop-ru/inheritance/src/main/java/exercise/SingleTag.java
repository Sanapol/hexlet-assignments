package exercise;

import java.util.Map;

// BEGIN
public class SingleTag extends Tag {

    public SingleTag(String tag, Map<String, String> tags) {
        super(tag, tags);
    }

    public String toString() {
        return "<" + tag + super.mapToString(tags) + ">";
    }
}
// END
