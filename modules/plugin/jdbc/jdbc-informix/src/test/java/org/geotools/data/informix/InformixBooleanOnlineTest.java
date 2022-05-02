/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2002-2022, Open Source Geospatial Foundation (OSGeo)
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation;
 *    version 2.1 of the License.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 */
package org.geotools.data.informix;

import org.geotools.data.FeatureReader;
import org.geotools.data.Query;
import org.geotools.data.Transaction;
import org.geotools.jdbc.JDBCBooleanTestSetup;
import org.geotools.jdbc.JDBCTestSupport;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;

public class InformixBooleanOnlineTest extends JDBCTestSupport {

    @Override
    protected JDBCBooleanTestSetup createTestSetup() {
        return new InformixBooleanTestSetup();
    }

    public void testGetSchema() throws Exception {
        SimpleFeatureType ft = dataStore.getSchema(tname("b"));
        assertEquals(Boolean.class, ft.getDescriptor("boolproperty").getType().getBinding());
    }

    public void testGetFeatures() throws Exception {
        try (FeatureReader r =
                dataStore.getFeatureReader(new Query(tname("b")), Transaction.AUTO_COMMIT)) {
            r.hasNext();
            SimpleFeature f = (SimpleFeature) r.next();
            assertEquals(Boolean.FALSE, f.getAttribute("boolproperty"));

            r.hasNext();
            f = (SimpleFeature) r.next();
            assertEquals(Boolean.TRUE, f.getAttribute("boolproperty"));
        }
    }
}