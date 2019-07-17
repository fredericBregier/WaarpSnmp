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
package org.waarp.snmp.utils;

import org.snmp4j.agent.DuplicateRegistrationException;
import org.snmp4j.agent.MOGroup;
import org.snmp4j.agent.MOServer;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.Variable;
import org.waarp.snmp.WaarpMOFactory;
import org.waarp.snmp.interf.WaarpInterfaceMib;

/**
 * MORow implementation for Waarp
 *
 * @author Frederic Bregier
 */
public class WaarpMORow implements MOGroup {
  /**
   * Type access
   */
  int[] type;
  /**
   * Base OID
   */
  OID reference;
  /**
   * MIB from which this Row is issued
   */
  WaarpInterfaceMib mib;
  /**
   * Mib Level entry identification
   */
  int mibLevel;
  /**
   * Row access
   */
  private WaarpMOScalar[] row;

  /**
   * @param mib
   * @param reference
   * @param entries
   * @param mibLevel this integer identifies this Row in the MIB
   */
  public WaarpMORow(WaarpInterfaceMib mib, OID reference, WaarpEntry[] entries,
                    int mibLevel) {
    this.mib = mib;
    this.reference = reference;
    this.mibLevel = mibLevel;
    setRow(new WaarpMOScalar[entries.length]);
    type = new int[entries.length];
    int[] ref = this.reference.getValue();
    int[] add = new int[2];
    add[1] = 0;
    for (int i = 0; i < entries.length; i++) {
      WaarpEntry entry = entries[i];
      type[i] = entry.smiConstantsType;
      add[0] = i + 1;
      OID oid = new OID(ref, add);
      // the value is null at the creation, meaning values have to be
      // setup once just after
      getRow()[i] = WaarpMOFactory.create(oid, null, entry.smiConstantsType,
                                          entry.access, this, mibLevel, i);
    }
  }

  /**
   * @return the row
   */
  public WaarpMOScalar[] getRow() {
    return row;
  }

  /**
   * @param row the row to set
   */
  void setRow(WaarpMOScalar[] row) {
    this.row = row;
  }

  /**
   * Set a Value in this Row
   *
   * @param index
   * @param value
   *
   * @throws IllegalArgumentException
   */
  public void setValue(int index, Object value)
      throws IllegalArgumentException {
    if (index >= getRow().length) {
      throw new IllegalArgumentException("Index exceed Row size");
    }
    Variable var = getRow()[index].getValue();
    WaarpMOFactory.setVariable(var, value, type[index]);
  }

  public void registerMOs(MOServer server, OctetString context)
      throws DuplicateRegistrationException {
    for (int i = 0; i < getRow().length; i++) {
      WaarpMOScalar scalar = getRow()[i];
      server.register(scalar, context);
    }
  }

  public void unregisterMOs(MOServer server, OctetString context) {
    for (int i = 0; i < getRow().length; i++) {
      WaarpMOScalar scalar = getRow()[i];
      server.unregister(scalar, context);
    }
  }
}
