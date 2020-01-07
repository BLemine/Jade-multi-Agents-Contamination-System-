package agents;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import java.util.concurrent.TimeUnit;
import support.PathRouter;
/**
 * C'est le policier mobile.
 * C'est l'agent responsable de la décontamination des noeuds,
 * il se déplace d'un container vers l'autre selon les règles
 * du déplacement qui gèrent son accessibilité (ces règles sont
 * définies par la classe support.PathRouter.java), une fois il 
 * trouve le virus, il le tue et envoie un message ACL vers le _fixCop_ pour lui informer, 
 * sinon il continue le procès de la décontamination.
 * 
 */


public class MobileCop extends Agent {
    // cette fonction est pour verifier si un agent exist dans un container ou non
    public boolean virusExists(String name, ContainerController container){
        try{
            AgentController potentailvirus = container.getAgent(name);
            return true;
        }catch(Exception ex){
            return false;
        }
    }
    @Override
    protected void setup() {
        System.out.println("Hello I am the MobileCop" + this.getAID().getName());
        final Agent aux= this;
        
        // le déplacement pour faire la décontamination
        addBehaviour(new OneShotBehaviour() {
            @Override
            public void action() {
                // TODO Auto-generated method stub
                try {
                    // il attend une minute(60sec) avant de commencer le procès de 
                    // décontamination.
                    System.out.println("i m waiting");
                    TimeUnit.SECONDS.sleep(60);
                    // puis il se déplace.
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
            // on verifie si l'agent VIRUS est dans le meme container
            if(virusExists("VIRUS",this.getContainerController())){
                ACLMessage message = new ACLMessage(ACLMessage.INFORM) ;
                String agentName = null;
                agentName = "rootCop" ;
                message.addReceiver(new AID(agentName , AID.ISLOCALNAME));
                // on informe l'agent policier stationnaire
                message.setContent("i got the virus");
                send(message) ;
                AgentController potentailvirus = this.getContainerController().getAgent("VIRUS");
                // l'agent policier mobile tue le virus dans ce cas
                potentailvirus.kill();
            }

            //création du message pour le procès de la décontamination
            ACLMessage message = new ACLMessage(ACLMessage.INFORM) ;
            String agentName = null;
            if( this.getContainerController().getContainerName().equals("Main-Container")){
                agentName = "rootCop" ;
            }else{
                agentName = "agent" + this.getContainerController().getContainerName();
            }
            /**
             * le policier mobile va informer l'agent auxiliaire "agentContainer-i" pour 
             * mettre à jour l'état de la décontamination
             */
            message.addReceiver(new AID(agentName , AID.ISLOCALNAME));
            message.setContent("MobileCop sending message");
            send(message) ;
            System.out.println("After the migration of the agent MobileCop : " + this.getAID().getName());
            System.out.println("To " + this.getContainerController().getContainerName());
            System.out.println("i m waiting");
            /**
             * pour se déplacer d'un noeud à un autre il attend quatre seconds 
             */
            TimeUnit.SECONDS.sleep(4);
            PathRouter.moveAgent(this);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
