/*******************************************************************************
 * Blanco2g
 * Copyright (C) 2011 Toshiki IGA
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
 * Copyright (c) 2011 Toshiki IGA and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *      Toshiki IGA - initial API and implementation
 *******************************************************************************/
package blanco.blanco2g;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import blanco.blanco2g.util.Blanco2gUtil;

/**
 * Blanco2g ドライバーのマネージャー
 * 
 * @author Toshiki IGA
 */
public class Blanco2gDriverManager {
    /**
     * ドライバーのリスト。
     */
    protected static final List<Blanco2gDriver> driverList = new ArrayList<Blanco2gDriver>();

    /**
     * 指定したドライバを DriverManager に登録します。
     * 
     * @param driver
     */
    public static synchronized void registerDriver(final Blanco2gDriver driver) {
        for (Blanco2gDriver lookup : driverList) {
            if (lookup.getClass().getCanonicalName().equals(driver.getClass().getCanonicalName())) {
                if (Blanco2gUtil.isDebug(Blanco2gDriverManager.class))
                    System.out.println("Driver '" + driver.getClass().getCanonicalName() + "' is already registered.");
                return;
            }
        }
        driverList.add(driver);

        // ドライバーを優先度でソートします。
        sortDriver(driverList);

        if (Blanco2gUtil.isDebug(Blanco2gUtil.isDebug(Blanco2gDriverManager.class)))
            System.out.println("Driver '" + driver.getClass().getCanonicalName() + "' is registered.");
    }

    /**
     * 指定したドライバを DriverManager から登録解除します。
     * 
     * @param driver
     */
    public static synchronized void deregisterDriver(final Blanco2gDriver driver) {
        for (Blanco2gDriver lookup : driverList) {
            if (lookup.getClass().getCanonicalName().equals(driver.getClass().getCanonicalName())) {
                driverList.remove(lookup);
                if (Blanco2gUtil.isDebug(Blanco2gUtil.isDebug(Blanco2gDriverManager.class)))
                    System.out.println("Driver '" + driver.getClass().getCanonicalName() + "' is deregistered.");

                // ドライバーを優先度でソートします。
                sortDriver(driverList);

                return;
            }
        }
    }

    /**
     * ドライバーのリストを取得します。
     * 
     * @return
     */
    public static List<Blanco2gDriver> getDriverList() {
        // Priority でソート済みのものを返却。
        return driverList;
    }

    /**
     * ドライバーを優先度でソートします。
     * 
     * @param drivers
     */
    public static void sortDriver(final List<Blanco2gDriver> drivers) {
        Collections.sort(drivers, new Comparator<Blanco2gDriver>() {
            //@Override
            public int compare(Blanco2gDriver arg0, Blanco2gDriver arg1) {
                return arg0.getPriority() - arg1.getPriority();
            }
        });
    }

    @Deprecated
    public static void showDrivers() {
        System.out.println("Blanco2g: registered driver list");
        final DecimalFormat format = new DecimalFormat("00000");
        for (Blanco2gDriver driver : getDriverList()) {
            System.out.println("  [" + format.format(driver.getPriority()) + "] "
                    + driver.getClass().getCanonicalName());
        }
    }
}
