/**
 * Copyright (c) 2000-2009, Jasig, Inc.
 * See license distributed with this file and available online at
 * https://www.ja-sig.org/svn/jasig-parent/tags/rel-10/license-header.txt
 */
package org.jasig.portal.portlet.container.properties;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import org.easymock.EasyMock;
import org.jasig.portal.channels.portlet.PortletHttpServletRequestWrapper;
import org.jasig.portal.portlet.om.IPortletWindow;
import org.jasig.portal.url.IPortalRequestUtils;
import org.springframework.mock.web.MockHttpServletRequest;

import junit.framework.TestCase;

/**
 * @author Eric Dalquist
 * @version $Revision$
 */
public class HttpRequestPropertiesManagerTest extends TestCase {
    private HttpRequestPropertiesManager httpRequestPropertiesManager;
    
    /* (non-Javadoc)
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        this.httpRequestPropertiesManager = new HttpRequestPropertiesManager();
    }

    /* (non-Javadoc)
     * @see junit.framework.TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        this.httpRequestPropertiesManager = null;
    }

    public void testGetRequestProperties() {
        final MockHttpServletRequest request = new MockHttpServletRequest();
        request.setAttribute(PortletHttpServletRequestWrapper.ATTRIBUTE__HTTP_SERVLET_REQUEST, request);
        request.setRemoteAddr("1.2.3.4");
        request.setMethod("POST");
        
        final IPortletWindow portletWindow = EasyMock.createMock(IPortletWindow.class);
        
        final IPortalRequestUtils portalRequestUtils = EasyMock.createMock(IPortalRequestUtils.class);
        EasyMock.expect(portalRequestUtils.getOriginalPortletAdaptorRequest(request)).andReturn(request);
        
        EasyMock.replay(portletWindow, portalRequestUtils);
        
        this.httpRequestPropertiesManager.setPortalRequestUtils(portalRequestUtils);
        
        final Map<String, String[]> properties = this.httpRequestPropertiesManager.getRequestProperties(request, portletWindow);

        assertNotNull("properties Map should not be null", properties);
        assertEquals("properties Map should have 2 values", 2, properties.size());
        assertEquals(Collections.singletonList("1.2.3.4"), Arrays.asList(properties.get("REMOTE_ADDR")));
        assertEquals(Collections.singletonList("POST"), Arrays.asList(properties.get("REQUEST_METHOD")));
        
        EasyMock.verify(portletWindow, portalRequestUtils);
    }
}
