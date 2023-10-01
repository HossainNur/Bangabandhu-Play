package com.durbar.bangabandhuplay.data.model.get_related_contents;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {
    @SerializedName("single_content_related_contents")
    @Expose
    public List<SingleContentRelatedContent> singleContentRelatedContents;
    @SerializedName("fixed_related_contents")
    @Expose
    public List<FixedRelatedContent> fixedRelatedContents;

    public Data(List<SingleContentRelatedContent> singleContentRelatedContents, List<FixedRelatedContent> fixedRelatedContents) {
        this.singleContentRelatedContents = singleContentRelatedContents;
        this.fixedRelatedContents = fixedRelatedContents;
    }

    public List<SingleContentRelatedContent> getSingleContentRelatedContents() {
        return singleContentRelatedContents;
    }

    public List<FixedRelatedContent> getFixedRelatedContents() {
        return fixedRelatedContents;
    }
}
