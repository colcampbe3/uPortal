/**
 * Copyright � 2001 The JA-SIG Collaborative.  All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. Redistributions of any form whatsoever must retain the following
 *    acknowledgment:
 *    "This product includes software developed by the JA-SIG Collaborative
 *    (http://www.jasig.org/)."
 *
 * THIS SOFTWARE IS PROVIDED BY THE JA-SIG COLLABORATIVE "AS IS" AND ANY
 * EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE JA-SIG COLLABORATIVE OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */

 package org.jasig.portal;

/**
 * Interface by which portal talks to the database
 * @author George Lindholm
 * @version $Revision$
 */

import org.w3c.dom.*;
import java.util.Hashtable;
import java.util.Enumeration;
import java.util.Vector;
import org.w3c.dom.Document;
import org.jasig.portal.security.IPerson;

public interface IUserLayoutStore {
  /* UserLayout  */
  public Document getUserLayout(IPerson Person,int profileId) throws Exception;
  public void setUserLayout(IPerson Person,int profileId,Document layoutXML, boolean channelsAdded) throws Exception;

  /* UserPreferences */
  public int getUserBrowserMapping(IPerson Person,String userAgent) throws Exception;
  public void setUserBrowserMapping(IPerson Person,String userAgent, int profileId) throws Exception;
  public UserProfile getUserProfileById(IPerson Person, int profileId) throws Exception;
  public Hashtable getUserProfileList(IPerson Person) throws Exception;
  public void setUserProfile(IPerson Person,UserProfile profile) throws Exception;
  public StructureStylesheetUserPreferences getStructureStylesheetUserPreferences(IPerson Person,int profileId,int stylesheetId) throws Exception;
  public ThemeStylesheetUserPreferences getThemeStylesheetUserPreferences(IPerson Person,int profileId,int stylesheetId) throws Exception;
  public void setStructureStylesheetUserPreferences(IPerson Person,int profileId, StructureStylesheetUserPreferences fsup) throws Exception;
  public void setThemeStylesheetUserPreferences(IPerson Person, int profileId, ThemeStylesheetUserPreferences ssup) throws Exception;
  public void updateUserProfile(IPerson Person,UserProfile profile) throws Exception;
  public UserProfile addUserProfile(IPerson Person,UserProfile profile) throws Exception;
  public void deleteUserProfile(IPerson Person,int profileId) throws Exception;

  /* ChannelRegistry */
  public void addChannel(int id, IPerson publisher, Document doc) throws Exception;
  public void addChannel(int id, IPerson publisher, Document doc, String catID[]) throws Exception;
  public void removeChannel(String chanID) throws Exception;
  public Document getChannelRegistryXML(IPerson person) throws Exception;
  public Document getChannelTypesXML() throws Exception;
  public void getCategoryXML(Document catsDoc, Element root, String role) throws Exception;
  public String getNextStructChannelId(IPerson person) throws Exception;
  public String getNextStructFolderId(IPerson person) throws Exception;
  public void approveChannel(int chanId, IPerson approver, java.util.Date approveDate) throws Exception;

  /* CoreStylesheetDescription */
    public Integer getStructureStylesheetId(String ssName) throws Exception;
    public Integer getThemeStylesheetId(String tsName) throws Exception;
    public Integer addThemeStylesheetDescription(ThemeStylesheetDescription tsd) throws Exception;
    public Integer addStructureStylesheetDescription(StructureStylesheetDescription ssd) throws Exception;
    public ThemeStylesheetDescription getThemeStylesheetDescription(int id) throws Exception;
    public StructureStylesheetDescription getStructureStylesheetDescription(int stylesheetId) throws Exception;
    public void updateStructureStylesheetDescription(StructureStylesheetDescription ssd) throws Exception;
    public void updateThemeStylesheetDescription(ThemeStylesheetDescription tsd) throws Exception;
    public void removeStructureStylesheetDescription(int stylesheetId) throws Exception;
    public void removeThemeStylesheetDescription(int stylesheetId) throws Exception;
    public Hashtable getStructureStylesheetList(String mimeType) throws Exception;
    public Hashtable getThemeStylesheetList(int structureStylesheetId) throws Exception;
  public void getMimeTypeList(Hashtable list) throws Exception;

  /* ReferenceAuthentication */
  public String[] getUserAccountInformation(String username) throws Exception;

  /* ReferenceDirectoryInfo
  Normally directory information would come from a real directory server using
  for example, LDAP.  The reference inplementation uses the database for
  directory information.
  */

  public String[] getUserDirectoryInformation(String username) throws Exception;

  /* Counters */
  public int getIncrementIntegerId(String tableName) throws Exception;
  public void createCounter(String tableName) throws Exception;
  public void setCounter(String tableName, int value) throws Exception;
}

