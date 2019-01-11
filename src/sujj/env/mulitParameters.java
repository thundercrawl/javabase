package sujj.env;

public class mulitParameters {
	static public StringBuffer getString(Object...objects)
	{
		logger.Log.entering();
		StringBuffer strs = new StringBuffer();
		if(objects!=null)
		{
			for(Object obj:objects)
			{
				strs.append(obj.toString());
				
			}
			
		}
		return strs;
		
	}
	static public void wrapper()
	{
		
		//hold
		
		//hold
		
		
		//hold
		getString("a");
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		logger.Log.info(getString("A","B","vccx").toString());
		wrapper();
	}

}
