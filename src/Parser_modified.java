import java.util.*;

public class Parser_modified
{
	static int c = 0;
	static String datatype = "";

	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("enter a statement:\n");
		String inp = sc.nextLine();
		if(!inp.endsWith(";"))
		{
			System.out.println("; missing");
			c++;
		}
		boolean b = true;
		String dec = "";
		String[] arrSplit = inp.split(";", -1);
		for (int j = 0; j < arrSplit.length-1 ; j++)
		{
			dec = arrSplit[j];
			//System.out.println(dec+" "+(j+1));
			if (dec.equals(""))

			{
				System.out.println("Extra Semi-colon");
				c++;
				break;
			}
			if (!(dec.startsWith("int ") || dec.startsWith("double "))) 
			{
				System.out.println("invalid datatype.");
				c++;
			} 
			else 
			{
				String aa[];
				aa = dec.split(" ");
				datatype = aa[0];
			}
			int space = dec.indexOf(" ");
			dec = dec.substring(space + 1);

			String tokens[] = dec.split(",", -1);
			if (b) {
				for (String t : tokens)
				{
					if (t.equals(""))
					{
						System.out.println("Extra comma.");
						c++;
					} else
						parse(t);
				}
			}
		}
		System.out.println("\nParsing complete. \nNumber of errors: " + c);

		sc.close();
	}

	public final static boolean isValidJavaIdentifier(String str) {
		if (!((str.charAt(0) >= 'a' && str.charAt(0) <= 'z') || (str.charAt(0) >= 'A' && str.charAt(0) <= 'Z')
				|| str.charAt(0) == '_'))
			return false;

		for (int i = 1; i < str.length(); i++) {
			if (!((str.charAt(i) >= 'a' && str.charAt(i) <= 'z') || (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z')
					|| (str.charAt(i) >= '0' && str.charAt(i) <= '9') || str.charAt(i) == '_'))
				return false;
		}
		return true;
	}

	private static void parse(String id)
	{
		id = id.replaceAll(";+$", "");
		if (id.contains("=")) {
			String abc[] = id.split("=", -1);
			id = abc[0];
			if (!abc[1].equals("")) 
			{
				checkValue(abc[1]);
			} else {
				System.out.println("Value missing.");
				c++;
			}
		}
		boolean isID = isValidJavaIdentifier(id);
		if (!isID) 
		{
			System.out.println(id + " is not a valid identifier.");
			c++;
		}
	}

	private static void checkValue(String value)
	{
		if (datatype.equals("int"))
			isStringInt(value);
		if (datatype.equals("double"))
			isStringDouble(value);

	}

	public static void isStringInt(String value) 
	{
		try {
			Integer.parseInt(value);
		} catch (NumberFormatException e) {
			System.out.println("Type mismatch.");
			c++;
		}
	}

	public static void isStringDouble(String value) 
	{
		try {
			Double.parseDouble(value);
		} catch (NumberFormatException e) {
			System.out.println("Type mismatch.");
			c++;
		}

	}
}