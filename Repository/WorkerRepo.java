package Repository;

import java.lang.*;
import java.util.ArrayList;
import Entity.*;
import Interface.*;

public class WorkerRepo implements IWorkerRepo
{
	DatabaseConnection dbc;
	
	public WorkerRepo()
	{
		dbc = new DatabaseConnection();
	}
	
	
	public Workers searchWorker(String workerId)
	{
		Workers w = null;
		String query = "SELECT `workerName`, `designation`, `salary` FROM `Workers` WHERE `workerId`='"+workerId+"';";     
		try
		{
		
			dbc.openConnection();
			dbc.result = dbc.st.executeQuery(query);
		
			while(dbc.result.next())
			{
				
				String wname = dbc.result.getString("workerName");
				String wdesignation = dbc.result.getString("designation");
				double wsalary = dbc.result.getDouble("salary");
				double serviceDuration = dbc.result.getDouble("service Duration");
				
				w = new Workers();
				w.setWorkerId(workerId);
				w.setWName(wname);
				w.setWdesignation(wdesignation);
				w.setWSalary(wsalary);
				w.setServiceDuration(serviceDuration);
			}
			
		}
		catch(Exception ex){System.out.println(ex.getMessage());}
		dbc.closeConnection();
		return w;
	}
	public String[][] getAllWorkers()
	{
		ArrayList<Workers> ar = new ArrayList<Workers>();
		String query = "SELECT * FROM Worker;";  
		
		try
		{
			dbc.openConnection();
			dbc.result = dbc.st.executeQuery(query);
			
		
			while(dbc.result.next())
			{
				String workerId = dbc.result.getString("workerid");
				String wname = dbc.result.getString("workerName");
				String wdesignation = dbc.result.getString("designation");
				double wsalary = dbc.result.getDouble("salary");
				double serviceDuration = dbc.result.getDouble("service duration");
				
				Workers w = new Workers(workerId,wname,wdesignation,wsalary,serviceDuration);
				ar.add(w);
			}
		}
		catch(Exception e){System.out.println(e.getMessage());}
		dbc.closeConnection();
		
		
		Object obj[] = ar.toArray();
		String data[][] = new String [ar.size()][5];
		
		for(int i=0; i<obj.length; i++)
		{
			data[i][0] = ((Workers)obj[i]).getWorkerId();
			data[i][1] = ((Workers)obj[i]).getWName();
			data[i][2] = ((Workers)obj[i]).getWdesignation();
			data[i][3] = (((Workers)obj[i]).getWSalary())+"";
			data[i][4] = (((Workers)obj[i]).getServiceDuration())+"";
		}
		return data;
	}
}












































