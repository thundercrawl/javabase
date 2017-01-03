package sujj.exceptions;


class NPE_connect
{
	public int  makeConnect()
	{
		logger.Log.info("make connect");
		return 0;

	}
}
class NPE_stateExecute
{
	public void execute(final int rt)
	{
		logger.Log.info("exeucte on connect");
	}
}
public class FoundNPE {

	public static void main(final String[] args) {
		// TODO Auto-generated method stub
		final NPE_stateExecute ex = new NPE_stateExecute();
		final NPE_connect conn = null;
		ex.execute(conn.makeConnect());
	}

}
