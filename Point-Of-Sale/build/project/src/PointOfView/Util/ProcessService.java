package PointOfView.Util;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class ProcessService extends Service<Void> implements InnerService {

    InnerService service;

    public ProcessService(InnerService service) {
	this.service = service;
    }

    @Override
    protected Task<Void> createTask() {
	return new Task<Void>() {
	    @Override
	    protected Void call() throws Exception {
		service.doSomeThing();
		return null;
	    }
	};
    }

}
