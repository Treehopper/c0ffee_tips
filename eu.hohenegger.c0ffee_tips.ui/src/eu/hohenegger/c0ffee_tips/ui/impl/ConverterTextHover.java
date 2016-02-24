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
		StringBuffer result = new StringBuffer();
		Display.getDefault().syncExec(() -> {
			try {
				result.append(textViewer.getDocument().get(hoverRegion.getOffset(), hoverRegion.getLength()));
			} catch (BadLocationException e) {
				//do nothing
			}
		});
		String hoverToken = result.toString();
		
		String int32 = null;
		
		if (hoverToken.startsWith("0x")) {
			int32 = ConverterUtil.convert(hoverToken);
		}
		if (int32 != null) {
			return format(hoverToken, int32);
		}
		return null;
	}

	private String format(String hoverToken, String tip) {
		String result = "<html>";
		result += String.format("<b>%s:</b>", hoverToken);
		result += "<br>";
		result += String.format("Integer: %s", tip);
		result += "</html>";
		return result;
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
