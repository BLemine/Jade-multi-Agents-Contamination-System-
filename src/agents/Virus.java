package agents;
/**
 * C'est l'agent qui répresente le virus
 * Le virus se deplace d'un noeud vers l'autre selon sa position initiale qui définit 
 * les positions accessibles.
 * Une fois le virus est déplacé d'un container à un autre, il envoie un message _ACL_ vers 
 * l'agent auxiliaire afin de mettre à jour l'état de contamination à _true_ (l'état est modifié 
 * par l'agent auxiliaire "agentContainer-i").
 */


public class Virus extends Agent {
    // Cette fonction est pour verifier si un agent exist dans un container donné
    public boolean virusExists(String name, ContainerController container){
        try{
            AgentController potentailcop = container.getAgent(name);
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
            /**
             * Si le virus tombe dans un noeud de l'un des policiers alors il sera mort 
             */
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
