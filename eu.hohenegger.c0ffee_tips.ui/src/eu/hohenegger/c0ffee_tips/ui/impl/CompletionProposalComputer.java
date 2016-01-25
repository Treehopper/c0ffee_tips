package eu.hohenegger.c0ffee_tips.ui.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.ui.text.java.ContentAssistInvocationContext;
import org.eclipse.jdt.ui.text.java.IJavaCompletionProposalComputer;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;

import eu.hohenegger.c0ffee_tips.ConverterUtil;

public class CompletionProposalComputer implements IJavaCompletionProposalComputer {

	private final class CompletionProposal implements ICompletionProposal {
		private final ContentAssistInvocationContext context;

		private CompletionProposal(ContentAssistInvocationContext context) {
			this.context = context;
		}

		@Override
		public String getDisplayString() {
			return String.format("Replace '%s' with '%s'.", getSelectedText(context), ConverterUtil.convert(getSelectedText(context)));
		}

		private String getSelectedText(final ContentAssistInvocationContext context) {
			Point selectedRange = context.getViewer().getSelectedRange();
			String string = "";
			try {
				string = context.getDocument().get(selectedRange.x, selectedRange.y);
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return string;
		}

		@Override
		public String getAdditionalProposalInfo() {
			return "Replace selected text";
		}

		@Override
		public IContextInformation getContextInformation() {
			return null;
		}

		/** Inserts the proposed completion at the cursor position */
		@Override
		public void apply(IDocument document) {
			String string = getSelectedText(context);
			String newString = ConverterUtil.convert(string);

			Point selectedRange = context.getViewer().getSelectedRange();
			int position = selectedRange.x;
			String currentText = document.get();
			int index = context.getInvocationOffset();
			String before = currentText.substring(0, index);
			String after = currentText.substring(index + selectedRange.y);
			document.set(before + newString + after);
			context.getViewer().setSelectedRange(position, + newString.length());
		}

		@Override
		public Point getSelection(IDocument document) {
			//no new selection
			return null;
		}

		@Override
		public Image getImage() {
			// TODO: icon
			return null;
		}
	}

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
