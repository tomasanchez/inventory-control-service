package org.inventory.control.model.warehouse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.inventory.control.exceptions.InvalidLocationException;
import org.junit.jupiter.api.Test;

public class LocationCodeTest {

    private static final String validPattern = "AL-00-00-IZ";

    @Test
    public void aValidPaternCreatesLocationCode() {
        LocationCode code = new LocationCode(validPattern);
        assertNotNull(code);
    }

    @Test
    public void anInvalidPatternThrowsInvalidLocation() {
        assertThrows(InvalidLocationException.class, () -> {
            new LocationCode("00-AA-00-GG");
        });
    }

    @Test
    public void areaIsSetCorrect() {
        assertEquals("AL", new LocationCode(validPattern).getArea());
    }

    @Test
    void halfwayIsSetCorrect() {
        assertEquals("00", new LocationCode(validPattern).getHalfway());
    }

    @Test
    void rackIsSetCorrect() {
        assertEquals("00", new LocationCode(validPattern).getRack());
    }

    @Test
    void faceIsSetCorrect() {
        assertEquals("IZ", new LocationCode(validPattern).getFace());
    }
}
