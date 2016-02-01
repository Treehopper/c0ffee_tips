package eu.hohenegger.c0ffee_tips.ui.impl;

import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

public class ProvisioningAction extends org.eclipse.equinox.p2.engine.spi.ProvisioningAction {

	@Override
	public IStatus execute(Map<String, Object> parameters) {
		new ForceDetailFormatter().forceFormatter(Long.class, "\"0x\" + new String(toHexString(value)).toUpperCase()");
		return Status.OK_STATUS;
	}

	@Override
	public IStatus undo(Map<String, Object> parameters) {
		//TODO
//		new ForceDetailFormatter().forceRemoveFormatter(Long.class, "\"0x\" + new String(toHexString(value)).toUpperCase()");
		return Status.OK_STATUS;
	}
}
