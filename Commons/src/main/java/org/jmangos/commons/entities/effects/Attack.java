package org.jmangos.commons.entities.effects;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.jmangos.commons.entities.SpellEffectEntity;

@Entity
@DiscriminatorValue("78")
public class Attack extends SpellEffectEntity {

    public Attack() {

    }

}