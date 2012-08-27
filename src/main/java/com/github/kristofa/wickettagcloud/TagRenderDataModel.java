package com.github.kristofa.wickettagcloud;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.wicket.model.IModel;

/**
 * Model that wraps an {@link IModel} with a {@link Collection} of {@link TagCloudTag tags} and which is responsible for
 * calculating the font sizes for each of the tags, and returning a {@link List} of {@link TagRenderData}.
 * 
 * @author kristof
 */
class TagRenderDataModel implements IModel<List<TagRenderData>> {

    private static final long serialVersionUID = 4437219443055280235L;
    private final IModel<Collection<TagCloudTag>> tagCollectionModel;
    private final int minFontSizeInPixels;
    private final int maxFontSizeInPixels;

    private List<TagRenderData> tagRenderDataList = null;

    /**
     * Creates new {@link TagRenderDataModel} instance.
     * 
     * @param tagCollectionModel The {@link TagCloudTag} model this model wraps.
     * @param minFontSizeInPixels The minimum font size in pixels for displaying tags.
     * @param maxFontSizeInPixels The maximum font size in pixels for displaying tags.
     */
    public TagRenderDataModel(final IModel<Collection<TagCloudTag>> tagCollectionModel, final int minFontSizeInPixels,
        final int maxFontSizeInPixels) {
        if (minFontSizeInPixels < 1 || maxFontSizeInPixels <= minFontSizeInPixels) {
            throw new IllegalStateException(
                "Font sizes should be >=1 and max font size should be > min font size. Submitted font sizes: min: "
                    + minFontSizeInPixels + " max: " + maxFontSizeInPixels);
        }

        this.tagCollectionModel = tagCollectionModel;
        this.minFontSizeInPixels = minFontSizeInPixels;
        this.maxFontSizeInPixels = maxFontSizeInPixels;
    }

    @Override
    public List<TagRenderData> getObject() {
        if (tagRenderDataList == null) {
            tagRenderDataList = buildTagRenderDataList();
        }
        return tagRenderDataList;

    }

    @Override
    public void setObject(final List<TagRenderData> object) {
        tagRenderDataList = object;
    }

    @Override
    public void detach() {
        if (tagRenderDataList != null) {
            tagRenderDataList.clear();
            tagRenderDataList = null;
        }
        tagCollectionModel.detach();
    }

    /**
     * Builds a List of {@link TagRenderData} based on the Collection of {@link TagCloudTag tags} provided by the input
     * model. It will calculate the font size for each of the tags, respecting the min/max font sizes, and wrap each provided
     * {@link TagCloudTag tags} in a {@link TagRenderData} object.
     * 
     * @return List of {@link TagRenderData}.
     */
    private List<TagRenderData> buildTagRenderDataList() {
        final List<TagRenderData> tagRenderData = new ArrayList<TagRenderData>();
        final Collection<TagCloudTag> tags = tagCollectionModel.getObject();

        int minWeight = Integer.MAX_VALUE;
        int maxWeight = Integer.MIN_VALUE;

        for (final TagCloudTag tag : tags) {
            minWeight = Math.min(minWeight, tag.getWeight());
            maxWeight = Math.max(maxWeight, tag.getWeight());
        }

        final int fontRange = maxFontSizeInPixels - minFontSizeInPixels;

        int weightRange = 1;
        if (minWeight != maxWeight) {
            weightRange = maxWeight - minWeight;
        }

        float weightIncrease = 0;
        if (fontRange >= weightRange) {
            weightIncrease = fontRange / weightRange;
        } else {
            weightIncrease = weightRange / fontRange;
        }

        for (final TagCloudTag tag : tags) {

            final int fontSize = Math.round(minFontSizeInPixels + (tag.getWeight() - minWeight) * weightIncrease);
            tagRenderData.add(new TagRenderData(tag, fontSize));
        }
        return tagRenderData;
    }

}
