/*******************************************************************************
 * Blanco2g
 * Copyright (C) 2011 NTT DATA Business Brains
 * 
 * This library is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
/*******************************************************************************
 * Copyright (c) 2011 NTT DATA Business Brains and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *      NTT DATA Business Brains - initial API and implementation
 *******************************************************************************/
package blanco.struts.driver;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * struts-config.xml のためのクラス。
 * 
 * @author Toshiki IGA
 */
public class Blanco2gStrutsConfigUtil {
    /**
     * 与えられた BODY 部分を含む struts-config.xml データを取得します。
     * 
     * @param body
     * @return
     */
    public static byte[] getStrutsConfigXml(final String body) {
        final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        try {
            final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outStream, "UTF-8"));
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            writer.write("<!DOCTYPE struts-config PUBLIC \"-//Apache Software Foundation//DTD Struts Configuration 1.3//EN\" \"http://struts.apache.org/dtds/struts-config_1_3.dtd\">\n");
            writer.write("<struts-config>\n");

            writer.write(body + "\n");

            writer.write("</struts-config>\n");
            writer.close();

            return outStream.toByteArray();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
