package sma.grupo3.Retailer.Agents.Customer.Behavior;

import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import sma.grupo3.Retailer.Agents.Customer.CustomerState;
import sma.grupo3.Retailer.Agents.Transporter.Data.CustomerOrderDelivery;
import sma.grupo3.Retailer.SharedDomain.Annotations.CustomerGuard;

@CustomerGuard
public class OnTransporterResponseCustomerGuard extends GuardBESA {
    @Override
    public void funcExecGuard(EventBESA eventBESA) {
        CustomerOrderDelivery orderDelivery = (CustomerOrderDelivery) eventBESA.getData();
        CustomerState state = (CustomerState) getAgent().getState();
        state.setPredictedTime(orderDelivery.getPredictedTime());
    }
}
