package support;

import jade.core.Agent;
import jade.core.ContainerID;
import jade.wrapper.ControllerException;

import java.awt.*;

public class PathRouter {


    public static void moveAgent(Agent agent) throws ControllerException {
        ContainerID dest =new ContainerID();

        List ContAttr=new List();
        /*
         * TODO code
         * */
        if(agent.getContainerController().getContainerName().equals("Container-1")) {
            String[] str= {"Main-Container","Container-3","Container-4"};
            dest.setName(str[(int)(2*Math.random())]);

        }
        else if(agent.getContainerController().getContainerName().equals("Main-Container")){
            String[] str= {"Container-1","Container-2"};
            dest.setName(str[(int)Math.random()]);
        }
        else if(agent.getContainerController().getContainerName().equals("Container-2")){
            dest.setName("Main-Container");
        }
        else if(agent.getContainerController().getContainerName().equals("Container-3")){
            dest.setName("Container-1");
        }
        else if(agent.getContainerController().getContainerName().equals("Container-4")){
            dest.setName("Container-1");
        }
        agent.doMove(dest);
    }



}
