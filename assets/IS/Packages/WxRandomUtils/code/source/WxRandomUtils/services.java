package WxRandomUtils;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
// --- <<IS-END-IMPORTS>> ---

public final class services

{
	// ---( internal utility methods )---

	final static services _instance = new services();

	static services _newInstance() { return new services(); }

	static services _cast(Object o) { return (services)o; }

	// ---( server methods )---




	public static final void generateRandomFirstName (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(generateRandomFirstName)>> ---
		// @sigtype java 3.5
		// [o] field:0:required firstName
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();		
		String value = (String)getRandomObjectFromList(firstNameList);
		IDataUtil.put( pipelineCursor, "firstName", value );
		pipelineCursor.destroy();
		// --- <<IS-END>> ---

                
	}



	public static final void generateRandomIPV4 (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(generateRandomIPV4)>> ---
		// @sigtype java 3.5
		// [o] field:0:required ipv4
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();		
		String value = generateRandomIPV4();
		IDataUtil.put( pipelineCursor, "ipv4", value );
		pipelineCursor.destroy();
			
		// --- <<IS-END>> ---

                
	}



	public static final void generateRandomLastName (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(generateRandomLastName)>> ---
		// @sigtype java 3.5
		// [o] field:0:required lastName
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();		
		String value = (String)getRandomObjectFromList(lastNameList);
		IDataUtil.put( pipelineCursor, "lastName", value );
		pipelineCursor.destroy();
		// --- <<IS-END>> ---

                
	}



	public static final void generateRandomNumber (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(generateRandomNumber)>> ---
		// @sigtype java 3.5
		// [i] object:0:required max
		// [o] object:0:required number
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();	
		
		int max = IDataUtil.getInt(pipelineCursor, "max" , 0);
		
		String value = new Integer(generateRandom(max)).toString();
		IDataUtil.put( pipelineCursor, "number", value);
		pipelineCursor.destroy();
		// --- <<IS-END>> ---

                
	}



	public static final void generateRandomNumericString (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(generateRandomNumericString)>> ---
		// @sigtype java 3.5
		// [i] object:0:required length
		// [o] field:0:required numeric
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();	
		
		int length = IDataUtil.getInt(pipelineCursor, "length" , 0);
		
		String value = generateRandomNumericString(length);
		IDataUtil.put( pipelineCursor, "numeric", value);
		pipelineCursor.destroy();
		// --- <<IS-END>> ---

                
	}



	public static final void generateRandomSSN (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(generateRandomSSN)>> ---
		// @sigtype java 3.5
		// [o] field:0:required ssn
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();		
		String value = generateRandomSSN();
		IDataUtil.put( pipelineCursor, "ssn", value );
		pipelineCursor.destroy();
		// --- <<IS-END>> ---

                
	}



	public static final void generateRandomText (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(generateRandomText)>> ---
		// @sigtype java 3.5
		// [i] object:0:required length
		// [o] field:0:required text
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();		
		
		int length = IDataUtil.getInt(pipelineCursor, "length" , 0);
		
		String value = generateRandomText(length);
		IDataUtil.put( pipelineCursor, "text", value );
		pipelineCursor.destroy();
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	private static Random hdrRndm = new Random(System.nanoTime());
	
	public static int generateRandom(int length){
		int value = hdrRndm.nextInt(length);
		return value;
	}
	
	public static String generateRandomIPV4(){
		return hdrRndm.nextInt(256) + "." + hdrRndm.nextInt(256) + "." + hdrRndm.nextInt(256) + "." + hdrRndm.nextInt(256);
	}
	
	public static String generateRandomSSN() {
		return generateRandomNumericString(3)+"-"+generateRandomNumericString(2)+"-"+generateRandomNumericString(4);
	}
	
	public static String generateRandomText(int StringLength) {
		if(StringLength == 0){
			return null;
		}
		StringBuffer returnVal = new StringBuffer();
		String[] vals = {"a","b","c","d","e","f","g","h","i","j",
				"k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
		for(int lp = 0;lp < StringLength; lp++){
			returnVal.append(vals[generateRandom(vals.length)]);
		}
		return returnVal.toString();
	}
	
	public static String generateRandomNumericString(int StringLength) {
		if(StringLength == 0){
			return null;
		}
		StringBuffer returnVal = new StringBuffer();
		String[] vals = {"0","1","2","3","4","5","6","7","8","9"};
		for(int lp = 0;lp < StringLength; lp++){
			returnVal.append(vals[generateRandom(vals.length)]);
		}
		return returnVal.toString();
	}
	
	public static Object getRandomObjectFromList(List objList) {
		if(objList == null || objList.size() == 0){
			return null;
		}else if(objList.size() == 1){
			return objList.get(0);
		}
		int lGetObjIndex = generateRandom(objList.size());
		return objList.get(lGetObjIndex);
	}
	
	public static final List<String> firstNameList = new ArrayList<String>();
	static {
		firstNameList.add("Joe");
		firstNameList.add("Sally");
		firstNameList.add("Sara");
		firstNameList.add("Holly");
		firstNameList.add("Frank");
		firstNameList.add("George");
		firstNameList.add("Henry");
		firstNameList.add("Eric");
		firstNameList.add("Tracey");
		firstNameList.add("Kyle");
		firstNameList.add("Hailey");
		firstNameList.add("Sue");
		firstNameList.add("Dan");
	}
	
	public static final List<String> lastNameList = new ArrayList<String>();
	static {
		lastNameList.add("Smith");
		lastNameList.add("Baker");
		lastNameList.add("Finley");
		lastNameList.add("Hunter");
		lastNameList.add("Furter");
		lastNameList.add("Carlson");
		lastNameList.add("Beaks");
		lastNameList.add("Jones");
		lastNameList.add("Hathaway");
		lastNameList.add("Lawson");
		lastNameList.add("Night");
		lastNameList.add("Yunts");
		lastNameList.add("Jenks");
		lastNameList.add("Williams");
		lastNameList.add("Potter");
		lastNameList.add("Henry");
		lastNameList.add("McKenny");
		lastNameList.add("Woods");
		lastNameList.add("Milborne");
		lastNameList.add("Winkler");
		lastNameList.add("Katz");
		lastNameList.add("Wilkinson");
		lastNameList.add("Barns");
		lastNameList.add("Cobra");
	}
	// --- <<IS-END-SHARED>> ---
}

