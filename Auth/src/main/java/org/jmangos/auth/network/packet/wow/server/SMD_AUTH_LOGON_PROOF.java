/*******************************************************************************
 * Copyright (C) 2013 JMaNGOS <http://jmangos.org/>
 * 
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the
 * Free Software Foundation; either version 2 of the License, or (at your
 * option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 * 
 * You should have received a copy of the GNU General Public License along
 * with this program. If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package org.jmangos.auth.network.packet.wow.server;

import org.jmangos.auth.network.packet.wow.AbstractWoWServerPacket;
import org.jmangos.commons.model.AccountInfo;
import org.jmangos.commons.model.WoWAuthResponse;
import org.jmangos.commons.network.model.State;

/**
 * The Class <tt>TCMD_AUTH_LOGON_PROOF</tt>.
 */
public class SMD_AUTH_LOGON_PROOF extends AbstractWoWServerPacket {

    /** The response. */
    private WoWAuthResponse response;

    /**
     * Instantiates a new <tt>TCMD_AUTH_LOGON_PROOF</tt>.
     */
    public SMD_AUTH_LOGON_PROOF() {

    }

    /**
     * Constructs new instance of <tt>TCMD_AUTH_LOGON_PROOF</tt> packet.
     * 
     * @param response
     *        the WoWAuthResponse
     */
    public SMD_AUTH_LOGON_PROOF(final WoWAuthResponse response) {

        this.response = response;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void writeImpl() {

        writeC(this.response.getMessageId());
        if (this.response == WoWAuthResponse.WOW_SUCCESS) {
            getChannel().setChannelState(State.AUTHED);
            writeB(((AccountInfo) (getChannel().getChanneledObject())).getM2());
            writeC(0);
            writeC(-128);
            writeC(0);
            writeC(0);
            // surveyId
            writeC(0);
            writeC(0);
            writeC(0);
            writeC(0);
            // unkFlags
            writeC(0);
            writeC(0);
        }
    }
}
