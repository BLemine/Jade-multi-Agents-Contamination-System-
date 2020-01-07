package agents;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class SimpleAgent extends Agent {

    public Boolean isContaminated = false ;

    public Boolean getContaminated() {
        return isContaminated;
    }

    public void setContaminated(Boolean contaminated) {
        isContaminated = contaminated;
    }

    @Override
    protected void setup() {
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage message = receive() ;
                if (message != null) {
                    System.out.println("the received message : "+message.getContent() + "From " + message.getSender().getName());
                    if ( message.getSender().getName().contains("VIRUS")){
                        setContaminated(true);
                        System.out.println("The contamination status : " + getContaminated().toString());
                    }else{
                        setContaminated(false);
                        System.out.println("The contamination status : " + getContaminated().toString());
                    }
                } else {
                    block() ;
                }
            }
        });
    }
}
