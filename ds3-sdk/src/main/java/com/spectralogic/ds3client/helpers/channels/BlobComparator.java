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

package com.spectralogic.ds3client.helpers.channels;

import com.spectralogic.ds3client.models.BulkObject;

import java.util.Comparator;

/**
 * Used to sort BlobApiBean (Blobs).  We only care about the starting
 * offset, so that is what is used to compare.
 */
public class BlobComparator implements Comparator<BulkObject> {
    @Override
    public int compare(final BulkObject o1, final BulkObject o2) {
        return Long.compare(o1.getOffset(), o2.getOffset());
    }

    public static Comparator<BulkObject> create() {
        return new BlobComparator();
    }
}
