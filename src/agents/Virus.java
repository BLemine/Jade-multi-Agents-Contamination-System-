package agents;

import jade.core.AID;
import jade.core.Agent;
import jade.core.ContainerID;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.AMSService;
import jade.domain.FIPAAgentManagement.AMSAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.lang.acl.ACLMessage;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.ControllerException;
import support.PathRouter;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Virus extends Agent {

    public boolean virusExists(String name, ContainerController container){
        try{
            AgentController potentailcop = container.getAgent("mobileCop");
            return true;
        }catch(Exception ex){
            return false;
        }
    }
    @Override
    protected void setup() {
        System.out.println("Hello I am the virus" + this.getAID().getName());
        final Agent aux= this;
        addBehaviour(new OneShotBehaviour() {
            @Override
            public void action() {
                // TODO Auto-generated method stub
                try {
                    System.out.println("i m waiting");
                    TimeUnit.SECONDS.sleep(7);
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
            if(virusExists("mobileCop",this.getContainerController()) || virusExists("fixCop",this.getContainerController())){
                ACLMessage message = new ACLMessage(ACLMessage.INFORM) ;
                String agentName = null;
                agentName = "fixCop" ;
                message.addReceiver(new AID(agentName , AID.ISLOCALNAME));
                message.setContent("i'm killed :(");
                send(message) ;
                this.getContainerController().getAgent("VIRUS").kill();
            }
            //creating the message
            ACLMessage message = new ACLMessage(ACLMessage.INFORM) ;
            String agentName = null;
            if( this.getContainerController().getContainerName().equals("Main-Container")){
                agentName = "fixCop" ;
            }else{
                agentName = "agent" + this.getContainerController().getContainerName();
            }
            message.addReceiver(new AID(agentName , AID.ISLOCALNAME));
            message.setContent("Bonjour c est bailahi");
            send(message) ;
            System.out.println("After migration : " + this.getAID().getName());
            System.out.println(" To " + this.getContainerController().getContainerName());
            System.out.println("i m waiting");
            TimeUnit.SECONDS.sleep(4);
            PathRouter.moveAgent(this);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    protected void takeDown() {
        super.takeDown();
        System.out.println("I'm dead :(");
    }
}
