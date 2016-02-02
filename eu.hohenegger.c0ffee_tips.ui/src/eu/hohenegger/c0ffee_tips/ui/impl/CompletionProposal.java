package eu.hohenegger.c0ffee_tips.ui.impl;

import org.eclipse.jdt.ui.text.java.ContentAssistInvocationContext;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;

import eu.hohenegger.c0ffee_tips.ConverterUtil;

final class CompletionProposal implements ICompletionProposal {
	private final ContentAssistInvocationContext context;

	CompletionProposal(ContentAssistInvocationContext context) {
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
		
		try {
			document.replace(context.getInvocationOffset(), selectedRange.y, newString);
		} catch (BadLocationException x) {
			// ignore
		}
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