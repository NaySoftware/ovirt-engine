package org.ovirt.engine.core.bll;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.ovirt.engine.core.common.businessentities.VDS;
import org.ovirt.engine.core.common.queries.NetworkIdParameters;
import org.ovirt.engine.core.compat.Guid;
import org.ovirt.engine.core.dao.VdsDAO;

/**
 * A test for the {@link GetVdsWithoutNetworkQuery} class. It tests the flow (i.e., that the query delegates properly to
 * the DAO}). The internal workings of the DAO are not tested.
 */
public class GetVdsWithoutNetworkQueryTest
        extends AbstractQueryTest<NetworkIdParameters,
        GetVdsWithoutNetworkQuery<NetworkIdParameters>> {

    @Test
    public void testExecuteQueryCommand() {
        // Set up the query parameters
        Guid networkId = Guid.NewGuid();
        when(params.getNetworkId()).thenReturn(networkId);

        // Set up the DAOs
        VDS vds = new VDS();
        List<VDS> expected = Collections.singletonList(vds);
        VdsDAO vdsDaoMock = mock(VdsDAO.class);
        when(vdsDaoMock.getAllWithoutNetwork(networkId)).thenReturn(expected);
        when(getDbFacadeMockInstance().getVdsDao()).thenReturn(vdsDaoMock);

        // Run the query
        GetVdsWithoutNetworkQuery<NetworkIdParameters> query = getQuery();
        query.executeQueryCommand();

        // Assert the result
        assertEquals("Wrong result returned", expected, getQuery().getQueryReturnValue().getReturnValue());
    }
}
