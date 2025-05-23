package org.easyschool.Generator;

import jakarta.persistence.SequenceGenerator;
import org.hibernate.generator.EventType;
import org.hibernate.generator.Generator;

import java.util.EnumSet;

public abstract class CustomSequenceGenerator implements Generator, SequenceGenerator {

    @Override
    public EnumSet<EventType> getEventTypes() {
        return EnumSet.of(EventType.INSERT);
    }

    @Override
    public String name() {
        return "";
    }
}
