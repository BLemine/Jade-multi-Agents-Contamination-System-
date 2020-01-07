package agents;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;

public class FixCop extends Agent {

    @Override
    protected void setup() {
        System.out.println("I am the FixedCop");
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage message = receive() ;
                if (message != null) {
                    System.out.println("receiving message "+message.getContent() + "From " + message.getSender().getName());
                } else {
                    block() ;
                }
            }
        });
    }
}
