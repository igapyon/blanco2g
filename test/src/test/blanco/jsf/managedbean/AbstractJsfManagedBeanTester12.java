/*******************************************************************************
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
 * Copyright (c) 2011 Toshiki IGA and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *      Toshiki IGA - initial API and implementation
 *******************************************************************************/
package test.blanco.jsf.managedbean;

import blanco.fw.BlancoInject;
import blanco.jsf.BlancoJsfManagedBean;

/**
 * 他の管理 Bean を参照する例。
 */
@BlancoJsfManagedBean
public abstract class AbstractJsfManagedBeanTester12 {
    /**
     * 他の管理 Bean を参照する例。
     * 
     * @param bean
     * @param strArg0
     * @return
     */
    public String sayHello(@BlancoInject final JsfManagedBeanTester01 bean, final String strArg0) {
        return "Hello: " + strArg0;
    }
}
