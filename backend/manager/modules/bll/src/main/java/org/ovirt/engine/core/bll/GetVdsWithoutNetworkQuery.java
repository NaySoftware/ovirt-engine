package org.ovirt.engine.core.bll;

import org.ovirt.engine.core.common.queries.NetworkIdParameters;

/**
 * A query to retrieve all Hosts that the given Network is not attached to, while the Network is assigned to their
 * Cluster
 */
public class GetVdsWithoutNetworkQuery<P extends NetworkIdParameters> extends QueriesCommandBase<P> {
    public GetVdsWithoutNetworkQuery(P parameters) {
        super(parameters);
    }

    @Override
    protected void executeQueryCommand() {
        getQueryReturnValue().setReturnValue(getDbFacade().getVdsDao()
                .getAllWithoutNetwork(getParameters().getNetworkId()));
    }
}
