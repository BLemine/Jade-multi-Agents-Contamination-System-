package agents;

import jade.core.AID;
import jade.core.Agent;
import jade.core.AgentContainer;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.ControllerException;
import support.PathRouter;

import java.util.concurrent.TimeUnit;

public class MobileCop extends Agent {
    public boolean virusExists(String name, ContainerController container){
        try{
            AgentController potentailvirus = container.getAgent("VIRUS");
            return true;
        }catch(Exception ex){
            return false;
        }
    }
    @Override
    protected void setup() {
        System.out.println("Hello I am the MobileCop" + this.getAID().getName());
        final Agent aux= this;
        addBehaviour(new OneShotBehaviour() {
            @Override
            public void action() {
                // TODO Auto-generated method stub
                try {
                    System.out.println("i m waiting");
                    TimeUnit.SECONDS.sleep(60);
                    PathRouter.moveAgent(aux);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void beforeMove() {
    }


    @Override
    protected void afterMove() {
        try {
            if(virusExists("VIRUS",this.getContainerController())){
                ACLMessage message = new ACLMessage(ACLMessage.INFORM) ;
                String agentName = null;
                agentName = "rootCop" ;
                message.addReceiver(new AID(agentName , AID.ISLOCALNAME));
                message.setContent("i got the virus");
                send(message) ;
                AgentController potentailvirus = this.getContainerController().getAgent("VIRUS");
                potentailvirus.kill();
            }
            //creating the message
            ACLMessage message = new ACLMessage(ACLMessage.INFORM) ;
            String agentName = null;
            if( this.getContainerController().getContainerName().equals("Main-Container")){
                agentName = "rootCop" ;
            }else{
                agentName = "agent" + this.getContainerController().getContainerName();
            }
            message.addReceiver(new AID(agentName , AID.ISLOCALNAME));
            message.setContent("MobileCop sending message");
            send(message) ;
            System.out.println("After the migration of the agent MobileCop : " + this.getAID().getName());
            System.out.println("To " + this.getContainerController().getContainerName());
            System.out.println("i m waiting");
            TimeUnit.SECONDS.sleep(4);
            PathRouter.moveAgent(this);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
