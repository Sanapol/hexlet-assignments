package exercise;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class PairedTag extends Tag {
    public String bodyTag;
    public List<Tag> tagList;

    public PairedTag(String tag, Map<String, String> tags, String bodyTag, List<Tag> tagList) {
        super(tag, tags);
        this.tagList = tagList;
        this.bodyTag = bodyTag;
    }

    public String toString() {
        return "<" + tag + super.mapToString(tags) + ">"
                + bodyTag + listToString(tagList) + "</" + tag + ">";
    }

    public String listToString(List<Tag> lists) {
        String result = "";
        for (var list : lists) {
            result += "<" + list.getTag() + super.mapToString(list.getTags()) + ">";
        }
        return result;
    }
}
// END
