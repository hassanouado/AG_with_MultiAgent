package ma.enset.bddc.sequenciel.SMA;

import jade.core.Agent;
import jade.core.ServiceDescriptor;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;

public class MasterAgent extends Agent {
    @Override
    protected void setup() {
        DFAgentDescription dfAgentDescription = new DFAgentDescription();
        dfAgentDescription.setName(getAID());
        ServiceDescription serviceDescription = new ServiceDescription();
        serviceDescription.setName("master");
        serviceDescription.setType("ga");
        dfAgentDescription.addServices(serviceDescription);

        try {
            DFService.register(this,dfAgentDescription);
        } catch (FIPAException e) {
           e.printStackTrace();
        }

        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage recive = receive();
                if(recive!=null){
                    System.out.println(recive.getSender()+""+recive.getContent());
                }else {
                    block();
                }
            }
        });
    }
}
