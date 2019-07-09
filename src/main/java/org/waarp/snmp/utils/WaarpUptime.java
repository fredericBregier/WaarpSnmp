/**
 * This file is part of Waarp Project.
 * <p>
 * Copyright 2009, Frederic Bregier, and individual contributors by the @author tags. See the COPYRIGHT.txt in the
 * distribution for a full listing of individual contributors.
 * <p>
 * All Waarp Project is free software: you can redistribute it and/or modify it under the terms of the GNU General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * <p>
 * Waarp is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License along with Waarp. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package org.waarp.snmp.utils;

import org.snmp4j.agent.mo.snmp.SysUpTime;
import org.snmp4j.smi.TimeTicks;

/**
 * Specific Value for TimeTicks for Uptime
 *
 * @author Frederic Bregier
 *
 */
@SuppressWarnings("serial")
public class WaarpUptime extends TimeTicks {
    protected SysUpTime type = null;

    /**
     *
     * @param type
     *            SysUpTime from SNMPV2
     */
    public WaarpUptime(SysUpTime type) {
        this.type = type;
        setInternalValue();
    }

    protected void setInternalValue() {
        setValue(type.get().getValue());
    }

    protected void setInternalValue(long value) {
        setValue(type.get().getValue());
    }

    @Override
    public Object clone() {
        setInternalValue();
        return super.clone();
    }

    @Override
    public long getValue() {
        setInternalValue();
        return super.getValue();
    }

}
