package Tools;

import Repositories.Repo_Variables;

public class msgWorker {
	private String msg;

	public String msgGen(Repo_Variables repoVar, boolean result, int iteration, int index) {

		if (result == false && index == 0)
		{
			msg = "Fail;Error en la/s siguiente/s iteracion/es: " + String.valueOf(iteration);
			repoVar.setDataMsg(msg);
		}

		if (result == false && index > 0)
		{
			msg = repoVar.getDataMsg() + ", " + String.valueOf(iteration);
			repoVar.setDataMsg(msg);
		}

		return repoVar.getDataMsg();
	}
}
