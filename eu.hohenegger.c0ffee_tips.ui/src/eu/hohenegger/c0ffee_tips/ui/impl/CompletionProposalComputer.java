package eu.hohenegger.c0ffee_tips.ui.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.ui.text.java.ContentAssistInvocationContext;
import org.eclipse.jdt.ui.text.java.IJavaCompletionProposalComputer;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContextInformation;

public class CompletionProposalComputer implements IJavaCompletionProposalComputer {

	@Override
	public List<ICompletionProposal> computeCompletionProposals(final ContentAssistInvocationContext context,
			IProgressMonitor monitor) {

		List<ICompletionProposal> res = new ArrayList<ICompletionProposal>();
		res.add(new CompletionProposal(context));
		return res;

	}

	@Override
	public List<IContextInformation> computeContextInformation(ContentAssistInvocationContext context,
			IProgressMonitor monitor) {
		return Collections.emptyList();
	}

	@Override
	public String getErrorMessage() {
		return "error during code completion";
	}

	@Override
	public void sessionStarted() {
		// TODO
	}

	@Override
	public void sessionEnded() {
		// TODO
	}
}
