/*******************************************************************************
 * This file is part of Waarp Project (named also Waarp or GG).
 *
 *  Copyright (c) 2019, Waarp SAS, and individual contributors by the @author
 *  tags. See the COPYRIGHT.txt in the distribution for a full listing of
 *  individual contributors.
 *
 *  All Waarp Project is free software: you can redistribute it and/or
 *  modify it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or (at your
 *  option) any later version.
 *
 *  Waarp is distributed in the hope that it will be useful, but WITHOUT ANY
 *  WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 *  A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along with
 *  Waarp . If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package org.waarp.snmp.interf;

import org.snmp4j.smi.Gauge32;

/**
 * Generic Gauge32 with update possibility for GoldenGate
 *
 * @author Frederic Bregier
 */
@SuppressWarnings("serial")
public abstract class WaarpGauge32 extends Gauge32 {
  public WaarpGauge32() {
    setInternalValue();
  }

  /**
   * Function to set the data before it is accessed by SNMP4J. This function
   * MUST call setValue(long)
   */
  protected abstract void setInternalValue();

  public WaarpGauge32(long value) {
    setInternalValue(value);
  }

  /**
   * Function to set the data before it is accessed by SNMP4J. This function
   * MUST call setValue(long)
   */
  protected abstract void setInternalValue(long value);

  @Override
  public long getValue() {
    setInternalValue();
    return super.getValue();
  }

  @Override
  public Object clone() {
    setInternalValue();
    return super.clone();
  }

  @Override
  public boolean isDynamic() {
    return true;
  }

}
