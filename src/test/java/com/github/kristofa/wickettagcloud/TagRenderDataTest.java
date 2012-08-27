package com.github.kristofa.wickettagcloud;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.apache.wicket.markup.html.link.AbstractLink;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for {@link TagRenderData}.
 * 
 * @author kristof
 */
public class TagRenderDataTest {

    private final static String TAG_NAME = "tagName";
    private final static int TAG_WEIGHT = 4;
    private final static String LINK_COMPONENT_ID = "LINKID";
    private final static int FONT_SIZE_IN_PIXELS = 12;

    private AbstractLink mockLink;
    private TagCloudTag mockTagCloudTag;
    private TagRenderData tagRenderData;

    @Before
    public void setup() {
        mockLink = mock(AbstractLink.class);
        mockTagCloudTag = mock(TagCloudTag.class);
        when(mockTagCloudTag.getName()).thenReturn(TAG_NAME);
        when(mockTagCloudTag.getWeight()).thenReturn(TAG_WEIGHT);
        when(mockTagCloudTag.getLink(LINK_COMPONENT_ID)).thenReturn(mockLink);

        tagRenderData = new TagRenderData(mockTagCloudTag, FONT_SIZE_IN_PIXELS);
    }

    /**
     * Tests {@link TagRenderData#getTagName()}.
     */
    @Test
    public void testGetTagName() {
        assertEquals(TAG_NAME, tagRenderData.getTagName());
        verify(mockTagCloudTag).getName();
        verifyNoMoreInteractions(mockTagCloudTag);
    }

    /**
     * Tests {@link TagRenderData#getLink(String)}.
     */
    @Test
    public void testGetLink() {
        assertEquals(mockLink, tagRenderData.getLink(LINK_COMPONENT_ID));
        verify(mockTagCloudTag).getLink(LINK_COMPONENT_ID);
        verifyNoMoreInteractions(mockTagCloudTag);
    }

    /**
     * Tests {@link TagRenderData#getFontSizeInPixels()}.
     */
    @Test
    public void testGetFontSizeInPixels() {
        assertEquals(FONT_SIZE_IN_PIXELS, tagRenderData.getFontSizeInPixels());
    }

}
