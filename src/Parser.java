import java.util.*;
public class Parser
{
	static int c=0;
	public static void main(String args[])
	{
		Scanner sc= new Scanner(System.in);
		
		String dec=sc.nextLine();
		if(!dec.startsWith("int "))
		{
			System.out.println("invalid datatype.");
			c++;
		}
		if(!dec.endsWith(";"))
		{
			System.out.println("; missing");
			c++;
		}
		int space= dec.indexOf(" ");
		dec=dec.substring(space+1);
		
		String tokens[]= dec.split(",", -1);
		for(String t: tokens)
		{
			if(t.equals(""))
			{
				System.out.println("Extra comma.");
				c++;
			}
			else
				parse(t);
		}
	
		System.out.println("\nParsing complete. \nNumber of errors: "+c);
	}
	
	public final static boolean isValidJavaIdentifier(String str)
	  {
		if (!((str.charAt(0) >= 'a' && str.charAt(0) <= 'z')  || (str.charAt(0) >= 'A' && str.charAt(0) <= 'Z') || str.charAt(0) == '_')) 
		        return false; 		  
		    
		for (int i = 1; i < str.length(); i++)
		{ 
			if (!((str.charAt(i) >= 'a' && str.charAt(i) <= 'z')|| (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z')  || (str.charAt(i) >= '0' && str.charAt(i) <= '9') || str.charAt(i)== '_')) 
				return false; 
		} 		   
	    return true; 	     
	  }
	
	private static void parse(String id)
	{
		id=id.replaceAll(";+$", "");
		if(id.contains("="))
		{
			String abc[]=id.split("=", -1);
			id=abc[0];
			if(!abc[1].equals(""))
			{
				checkValue(abc[1]);						
			}
			else
			{
				System.out.println("Value missing.");
				c++;
			}
		}
		//System.out.println(id);
		boolean isID= isValidJavaIdentifier(id);
		if(!isID)
		{
			System.out.println(id+ " is not a valid identifier.");
			c++;
		}
	}
	private static void checkValue(String value)
	{
		try
		{
			int num= Integer.parseInt(value);			
		}
		catch(NumberFormatException e)
		{
			System.out.println("Type mismatch.");
			c++;
		}		
	}
}
