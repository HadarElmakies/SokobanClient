package general;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

import commands.Command;
import commands.ExitCommand;


public  class Controller{
	//data members
	private BlockingQueue<Command> commandsQueue;
	boolean stop=false;
	private Command ExitCommand= new ExitCommand();

public Controller() {
	commandsQueue= new ArrayBlockingQueue<Command>(10);
}

public void start() {
	new Thread(new Runnable() {


		@Override
		public void run() {
			while(stop==false)
			{
				try {
					Command c=commandsQueue.poll(10,TimeUnit.SECONDS);

					if(c!=null){
						if(c.getClass()==ExitCommand.getClass()){
							c.execute();
							stop();
						}
						else{
							System.out.println(c.getClass());
							c.execute();
						}
					}


				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}).start();
}


public void stop(){
	stop=true;
}

public void insertCommand(Command c){

		try {
			commandsQueue.put(c);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


}
}
