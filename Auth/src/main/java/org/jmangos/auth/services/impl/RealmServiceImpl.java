/*******************************************************************************
 * Copyright (C) 2012 JMaNGOS <http://jmangos.org/>
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
 *******************************************************************************/
package org.jmangos.auth.services.impl;

import java.util.List;

import org.criteria4jpa.criterion.Criterion;
import org.jmangos.auth.dao.RealmDao;
import org.jmangos.auth.model.RealmDto;
import org.jmangos.auth.services.RealmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("realmService")
public class RealmServiceImpl implements RealmService {
    
    @Autowired
    private RealmDao realmDao;
    
    @Override
    public RealmDto readRealm(final Long id) {
    
        return this.realmDao.readRealm(id);
    }
    
    @Override
    public List<RealmDto> readRealms(final Criterion... criterions) {
    
        return this.realmDao.readRealms(criterions);
    }
    
    @Override
    public Long createOrUpdateRealm(final RealmDto realmDto) {
    
        return this.realmDao.createOrUpdateRealm(realmDto);
    }
    
    @Override
    public void deleteRealm(final RealmDto realmDto) {
    
        this.realmDao.deleteRealm(realmDto);
    }
    
}
