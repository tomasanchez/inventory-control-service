package org.inventory.api.shipment;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

public class MeliAdapterTest {

    private MeliAdapter meliAdapter;

    @BeforeEach
    void setUpAdapter() {
        meliAdapter = new MeliAdapter(new RestTemplate());
    }

    @Test
    void testGetShipment() {
        assertNotNull(meliAdapter.getShipment("MLA813727183"));
    }
}
