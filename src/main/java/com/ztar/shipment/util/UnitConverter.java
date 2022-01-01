package com.ztar.shipment.util;

import com.ztar.shipment.model.Unit;
import org.apache.commons.collections4.map.MultiKeyMap;

public final class UnitConverter {

    public static final MultiKeyMap<Unit, Double> units = new MultiKeyMap<>();

    static {
        units.put(Unit.INCH, Unit.INCH, 1.0);
        units.put(Unit.CM, Unit.INCH, 0.393701);
        units.put(Unit.POUND, Unit.POUND, 1.0);
        units.put(Unit.GRAM, Unit.POUND, 0.00220462);
    }

    private UnitConverter() {

    }

    public static Double convert(Integer value, Unit source, Unit destination) {
        return value * units.get(source, destination);
    }
}
