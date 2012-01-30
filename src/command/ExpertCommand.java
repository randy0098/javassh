package command;


public class ExpertCommand extends Command {
	String commandContent = "expert\r";
	String commandReturnBack = "";
	String password;
	
	public ExpertCommand(){
		
	}
	
	public ExpertCommand(String password){
		this.password = password;
	}
	
	public String getPassword()
    {
    	return password;
    }
	public void setPassword(String password)
    {
    	this.password = password;
    }
	
}
