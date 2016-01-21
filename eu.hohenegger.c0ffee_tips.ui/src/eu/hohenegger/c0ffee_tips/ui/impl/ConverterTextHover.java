package eu.hohenegger.c0ffee_tips.ui.impl;

import org.eclipse.jdt.ui.text.java.hover.IJavaEditorTextHover;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;

import eu.hohenegger.c0ffee_tips.ConverterUtil;

public class ConverterTextHover implements /*ITextHoverExtension2, ITextHoverExtension,*/ IJavaEditorTextHover {

	@Override
	public String getHoverInfo(ITextViewer textViewer, IRegion hoverRegion) {
		String[] result = new String[1];
		Display.getDefault().syncExec(() -> {
			Point selectedRange = textViewer.getSelectedRange();
			try {
				String tip = null;
				String string = textViewer.getDocument().get(selectedRange.x, selectedRange.y);
				if (string.startsWith("0x")) {
					tip = ConverterUtil.convert(string);
				}
				result[0] = tip;
			} catch (BadLocationException e) {
				result[0] = null;
			}

		});
		return result[0];
	}

	@Override
	public IRegion getHoverRegion(ITextViewer textViewer, int offset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setEditor(IEditorPart editor) {
		// TODO Auto-generated method stub

	}
}
