/*
 * ****************************************************************************
 *    Copyright 2014-2016 Spectra Logic Corporation. All Rights Reserved.
 *    Licensed under the Apache License, Version 2.0 (the "License"). You may not use
 *    this file except in compliance with the License. A copy of the License is located at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *    or in the "license" file accompanying this file.
 *    This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 *    CONDITIONS OF ANY KIND, either express or implied. See the License for the
 *    specific language governing permissions and limitations under the License.
 *  ****************************************************************************
 */
package com.spectralogic.ds3client.metadata;

import com.google.common.collect.ImmutableMap;
import com.spectralogic.ds3client.helpers.Ds3ClientHelpers;
import com.spectralogic.ds3client.metadata.interfaces.MetadataRestore;
import com.spectralogic.ds3client.metadata.interfaces.MetadataRestoreListener;
import com.spectralogic.ds3client.metadata.interfaces.MetadataStore;
import com.spectralogic.ds3client.metadata.interfaces.MetadataStoreListener;
import com.spectralogic.ds3client.networking.Metadata;
import com.spectralogic.ds3client.utils.MetaDataUtil;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.PrintStream;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;

public class Metadata_Test {
    private final File file = new File(getClass().getClassLoader().getResource("LoremIpsum.txt").getFile());
    private final ImmutableMap.Builder<String, String> mMetadataMap = new ImmutableMap.Builder<>();
    private final FileSystem store = file.toPath().getFileSystem();
    private final WindowsMetadataStore windowsMetaDataStore = new WindowsMetadataStore(mMetadataMap, new MetadataStoreListener() {
        @Override
        public void onMetadataFailed(final String message) {
        }
    });
    private final PosixMetadataStore posixMetaDataStore = new PosixMetadataStore(mMetadataMap, new MetadataStoreListener() {
        @Override
        public void onMetadataFailed(final String message) {
        }
    });

    @Test
    public void getOperatingSystem() {
        assertEquals(System.getProperty("os.name"), MetaDataUtil.getOS());
    }

    @Test
    public void getFileRealPath() {
        assertEquals(file.getParent() + "/" + file.getName(), MetaDataUtil.getRealFilePath(file.getPath(), file.getName()));
    }

    @Test
    public void saveFileWindowsTime() {
        if (System.getProperty("os.name").contains("Windows")) {
            try {
                final BasicFileAttributes basicFileAttributes = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
                //assertEquals(String.valueOf(basicFileAttributes.creationTime().toMillis()), windowsMetaDataStore.saveCreationTimeMetaData(basicFileAttributes));
                //assertEquals(String.valueOf(basicFileAttributes.lastAccessTime().toMillis()), windowsMetaDataStore.saveAccessTimeMetaData(basicFileAttributes));
                //assertEquals(String.valueOf(basicFileAttributes.lastModifiedTime().toMillis()), windowsMetaDataStore.saveLastModifiedTime(basicFileAttributes));
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void getFileWindowsTime() {
        if (System.getProperty("os.name").contains("Windows")) {
            try {
               // assertEquals(null, windowsMetaDataStore.getCreationTime());
               // assertEquals(null, windowsMetaDataStore.getAccessTime());
               // assertEquals(null, windowsMetaDataStore.getLastModified());
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void getSupportedFileAttributes() {
        assertEquals(store.supportedFileAttributeViews(), MetaDataUtil.getSupportedFileAttributes(file.toPath()));
    }

    @Test
    public void saveFileIds() {
        if (!System.getProperty("os.name").contains("Windows")) {
            try {
              //  assertEquals(String.valueOf(Files.getAttribute(file.toPath(), "unix:uid")), posixMetaDataStore.saveGroupId(file.toPath()));
               // assertEquals(String.valueOf(Files.getAttribute(file.toPath(), "unix:uid")), posixMetaDataStore.saveGroupId(file.toPath()));
               // assertEquals(String.valueOf(Files.getAttribute(file.toPath(), "unix:uid")), posixMetaDataStore.saveGroupId(file.toPath()));
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void saveOSMeta() {
        try {
            assertEquals(System.getProperty("os.name"), windowsMetaDataStore.saveOSMetaData(System.getProperty("os.name")));
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
