package exercise;

// BEGIN
public class LabelTag implements TagInterface {
    private String tag;
    private TagInterface doterTags;

    public LabelTag(String tag, TagInterface doterTags) {
        this.tag = tag;
        this.doterTags = doterTags;
    }

    public String render() {
        return "<label>" + tag + doterTags.render() + "</label>";
    }
}
// END
