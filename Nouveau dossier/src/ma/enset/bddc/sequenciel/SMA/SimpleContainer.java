package ma.enset.bddc.sequenciel.SMA;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;
import ma.enset.bddc.sequenciel.GAUtils;

public class SimpleContainer {
    public static void main(String[] args) throws StaleProxyException {
        Runtime runtime = Runtime.instance();
        ProfileImpl profile=new ProfileImpl();
        profile.setParameter("gui","true");
        AgentContainer agentContainer = runtime.createAgentContainer(profile);
        AgentController masterAgent = agentContainer.createNewAgent("MasterAgent", MasterAgent.class.getName(), new Object[]{});
        masterAgent.start();
        for (int i=0;i<GAUtils.ISLAND_NUMBER;i++) {
            AgentController newAgent = agentContainer.createNewAgent("Island" + i, IslanAgent.class.getName(), new Object[]{});
            newAgent.start();
        }
    }
}
