package com.github.kristofa.wickettagcloud;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.wicket.model.IModel;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for {@link TagRenderDataModel}.
 * 
 * @author kristof
 */
public class TagRenderDataModelTest {

    private final static int MIN_FONT_SIZE = 8;
    private final static int MAX_FONT_SIZE = 20;

    private final static String TAG1_NAME = "TAG1";
    private final static int TAG1_WEIGHT = 1;

    private final static String TAG2_NAME = "TAG2";
    private final static int TAG2_WEIGHT = 3;

    private final static String TAG3_NAME = "TAG3";
    private final static int TAG3_WEIGHT = 6;

    private final static String TAG4_NAME = "TAG4";
    private final static int TAG4_WEIGHT = 10;

    private IModel<Collection<TagCloudTag>> mockModel;
    private TagCloudTag tag1;
    private TagCloudTag tag2;
    private TagCloudTag tag3;
    private TagCloudTag tag4;

    @SuppressWarnings("unchecked")
    @Before
    public void setup() {
        mockModel = mock(IModel.class);

        tag1 = mock(TagCloudTag.class);
        when(tag1.getWeight()).thenReturn(TAG1_WEIGHT);
        when(tag1.getName()).thenReturn(TAG1_NAME);

        tag2 = mock(TagCloudTag.class);
        when(tag2.getWeight()).thenReturn(TAG2_WEIGHT);
        when(tag2.getName()).thenReturn(TAG2_NAME);

        tag3 = mock(TagCloudTag.class);
        when(tag3.getWeight()).thenReturn(TAG3_WEIGHT);
        when(tag3.getName()).thenReturn(TAG3_NAME);

        tag4 = mock(TagCloudTag.class);
        when(tag4.getWeight()).thenReturn(TAG4_WEIGHT);
        when(tag4.getName()).thenReturn(TAG4_NAME);

    }

    /**
     * Tests constructor in case min font size is invalid (<1).
     */
    @Test(expected = IllegalStateException.class)
    public void testConstructorMinFontSizeSmallerThenOne() {
        new TagRenderDataModel(mockModel, 0, MAX_FONT_SIZE);
    }

    /**
     * Tests constructor in case max font size is invalid (=minFontSize and should be larger.).
     */
    @Test(expected = IllegalStateException.class)
    public void testConstructorMaxFontSizeEqualToMinFontSize() {
        new TagRenderDataModel(mockModel, MAX_FONT_SIZE, MAX_FONT_SIZE);
    }

    /**
     * Tests {@link TagRenderDataModel#getObject()}. This method creates for each {@link TagCloudTag} provided by model, a
     * {@link TagRenderData} object and calculates the font sizes based on weight for each tag.
     */
    @Test
    public void testGetObject() {
        final Collection<TagCloudTag> tagCollection = Arrays.asList(tag1, tag2, tag3, tag4);
        when(mockModel.getObject()).thenReturn(tagCollection);

        final TagRenderDataModel tagRenderDataModel = new TagRenderDataModel(mockModel, MIN_FONT_SIZE, MAX_FONT_SIZE);
        final List<TagRenderData> tagRenderData = tagRenderDataModel.getObject();

        assertEquals("Expect a TagRenderData element for each input tag.", 4, tagRenderData.size());

        final TagRenderData firstTagRenderDataObject = tagRenderData.get(0);
        assertEquals("The model should not change order of tags.", TAG1_NAME, firstTagRenderDataObject.getTagName());
        assertEquals("Tag with lowest weight should get min fontsize.", MIN_FONT_SIZE,
            firstTagRenderDataObject.getFontSizeInPixels());

        final TagRenderData secondTagRenderDataObject = tagRenderData.get(1);
        assertEquals("The model should not change order of tags.", TAG2_NAME, secondTagRenderDataObject.getTagName());
        assertTrue("Tag font size should be bigger then tag with lower weight.",
            secondTagRenderDataObject.getFontSizeInPixels() > firstTagRenderDataObject.getFontSizeInPixels());

        final TagRenderData thirthTagRenderDataObject = tagRenderData.get(2);
        assertEquals("The model should not change order of tags.", TAG3_NAME, thirthTagRenderDataObject.getTagName());
        assertTrue("Tag font size should be bigger then tag with lower weight.",
            thirthTagRenderDataObject.getFontSizeInPixels() > secondTagRenderDataObject.getFontSizeInPixels());

        final TagRenderData forthTagRenderDataObject = tagRenderData.get(3);
        assertEquals("The model should not change order of tags.", TAG4_NAME, forthTagRenderDataObject.getTagName());
        assertTrue("Tag font size should be bigger then tag with lower weight.",
            forthTagRenderDataObject.getFontSizeInPixels() > thirthTagRenderDataObject.getFontSizeInPixels());
    }

    /**
     * Tests {@link TagRenderDataModel#getObject()}. This method creates for each {@link TagCloudTag} provided by model, a
     * {@link TagRenderData} object and calculates the font sizes based on weight for each tag.
     * <p>
     * This test tests the case where we only have 1 Tag. This initially triggered a division by zero exception which we
     * fixed. This test will guarantee we don't run into this issue anymore in future.
     */
    @Test
    public void testGetObjectWithSingleTag() {
        final Collection<TagCloudTag> tagCollection = Arrays.asList(tag1);
        when(mockModel.getObject()).thenReturn(tagCollection);

        final TagRenderDataModel tagRenderDataModel = new TagRenderDataModel(mockModel, MIN_FONT_SIZE, MAX_FONT_SIZE);
        final List<TagRenderData> tagRenderData = tagRenderDataModel.getObject();

        assertEquals("Expect a TagRenderData element for each input tag.", 1, tagRenderData.size());

        final TagRenderData firstTagRenderDataObject = tagRenderData.get(0);
        assertEquals("The model should not change order of tags.", TAG1_NAME, firstTagRenderDataObject.getTagName());
        assertEquals("Tag with lowest weight should get min fontsize.", MIN_FONT_SIZE,
            firstTagRenderDataObject.getFontSizeInPixels());

    }

    /**
     * Tests {@link TagRenderDataModel#detach()}.
     */
    @Test
    public void testDetach() {
        final Collection<TagCloudTag> tagCollection = Arrays.asList(tag1, tag2, tag3, tag4);
        when(mockModel.getObject()).thenReturn(tagCollection);

        final TagRenderDataModel tagRenderDataModel = new TagRenderDataModel(mockModel, MIN_FONT_SIZE, MAX_FONT_SIZE);
        final List<TagRenderData> tagRenderData = tagRenderDataModel.getObject();

        assertSame("A second call to getObject without detach should give us same object as first time.", tagRenderData,
            tagRenderDataModel.getObject());

        tagRenderDataModel.detach();

        assertNotSame(
            "After a detach, previous object should be nulled, and so a new call to getObject should give us a new object.",
            tagRenderData, tagRenderDataModel.getObject());

        tagRenderDataModel.detach();
        tagRenderDataModel.detach(); // detaching twice should work as well without throwing exception.
    }

    /**
     * Tests {@link TagRenderDataModel#setObject(List)}
     */
    @Test
    public void testSetObject() {
        final TagRenderDataModel tagRenderDataModel = new TagRenderDataModel(mockModel, MIN_FONT_SIZE, MAX_FONT_SIZE);
        final List<TagRenderData> emptyList = new ArrayList<TagRenderData>();

        tagRenderDataModel.setObject(emptyList);
        assertSame(emptyList, tagRenderDataModel.getObject());

    }

}
