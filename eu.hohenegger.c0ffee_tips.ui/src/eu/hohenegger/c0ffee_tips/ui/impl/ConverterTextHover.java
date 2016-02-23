package eu.hohenegger.c0ffee_tips.ui.impl;

import org.eclipse.jdt.ui.text.java.hover.IJavaEditorTextHover;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;

import eu.hohenegger.c0ffee_tips.ConverterUtil;

public class ConverterTextHover implements /*ITextHoverExtension2, ITextHoverExtension,*/ IJavaEditorTextHover {

	@Override
	public String getHoverInfo(ITextViewer textViewer, IRegion hoverRegion) {
		String[] result = new String[1];
		int i = 0x1d;
		Display.getDefault().syncExec(() -> {
			try {
				String tip = null;
				String string = textViewer.getDocument().get(hoverRegion.getOffset(), hoverRegion.getLength());;
				if (string.startsWith("0x")) {
					tip = ConverterUtil.convert(string);
				}
				if (tip != null) {
					result[0] = String.format("%s=%s", string, tip);
				}
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
