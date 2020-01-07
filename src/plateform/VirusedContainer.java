package plateform;
import jade.core.AgentContainer;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;
/*
 * Authors : BAILLAHI & DAKIR
 * */
/**
 * Cette classe défini le container ou disons le noeud départ du Virus; le noeud par lequel va 
 * commencer le Virus son processus de contamination.
 * Et bien sûr comme autre container, ce container contient l'agent auxillière pour la gestion 
 * des états de contamination.
 */
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;

public class VirusedContainer {

    public static void main(String[] args) {
        try {
            Runtime runtime = Runtime.instance();
            Profile profile = new ProfileImpl(false);
            profile.setParameter(Profile.MAIN_HOST, "localhost");
            AgentContainer agentContainer = runtime.createAgentContainer(profile);

            // attachons l'agent VIRUS au container VirusedContainer
            AgentController virusedAgent = agentContainer.createNewAgent("VIRUS", "agents.Virus", new Object[]{});

            String agentName = "agent" + agentContainer.getContainerName();

            AgentController simpleAgent = agentContainer.createNewAgent(agentName, "agents.SimpleAgent", new Object[]{"isContaminated", true}) ;
            simpleAgent.start();
            virusedAgent.start();


        } catch (ControllerException e) {
            e.printStackTrace();
        }

    }
}
