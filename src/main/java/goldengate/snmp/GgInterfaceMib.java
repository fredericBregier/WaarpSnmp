/**
   This file is part of GoldenGate Project (named also GoldenGate or GG).

   Copyright 2009, Frederic Bregier, and individual contributors by the @author
   tags. See the COPYRIGHT.txt in the distribution for a full listing of
   individual contributors.

   All GoldenGate Project is free software: you can redistribute it and/or 
   modify it under the terms of the GNU General Public License as published 
   by the Free Software Foundation, either version 3 of the License, or
   (at your option) any later version.

   GoldenGate is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with GoldenGate .  If not, see <http://www.gnu.org/licenses/>.
 */
package goldengate.snmp;

import org.snmp4j.agent.MOGroup;
import org.snmp4j.agent.MOScope;
import org.snmp4j.agent.NotificationOriginator;
import org.snmp4j.agent.mo.snmp.SNMPv2MIB;
import org.snmp4j.smi.OID;


/**
 * Interface for All MIBs in GoldenGate project
 * 
 * @author Frederic Bregier
 *
 */
public interface GgInterfaceMib extends MOGroup {
    /**
     * Set the agent
     * @param agent
     */
    public abstract void setAgent(GgSnmpAgent agent);
    /**
     * 
     * @return the base OID
     */
    public abstract OID getBaseOid();
    /**
     * 
     * @return the SNMPv2MIB associated with this MIB
     */
    public abstract SNMPv2MIB getSNMPv2MIB();
    /**
     * @param notificationOriginator
     * @param element
     * @param message
     * @param number
     */
    public abstract void notify(NotificationOriginator notificationOriginator, 
            OID oid, String message, int number);
    /**
     * Update the row for these services
     * 
     * @param scalar
     */
    public abstract void updateServices(GgMOScalar scalar);
    /**
     * Update the row for these services
     * 
     * @param range
     */
    public abstract void updateServices(MOScope range);
}
