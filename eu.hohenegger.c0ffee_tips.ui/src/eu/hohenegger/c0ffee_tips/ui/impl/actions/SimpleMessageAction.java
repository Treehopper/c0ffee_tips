package eu.hohenegger.c0ffee_tips.ui.impl.actions;


import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;
import org.eclipse.ui.internal.editors.text.EditorsPlugin;
import org.eclipse.ui.texteditor.AbstractTextEditor;

import eu.hohenegger.c0ffee_tips.ui.impl.Activator;
 
public class SimpleMessageAction implements IObjectActionDelegate {
	private Shell shell;
 
	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
	}
 
 
	@Override
	public void run(IAction action) {
		try {
			//get editor
			IEditorPart editorPart = Activator.getDefault().getWorkbench()
					.getActiveWorkbenchWindow().getActivePage()
					.getActiveEditor();
 
			if (!(editorPart instanceof AbstractTextEditor)) {
				return;
			}
			int offset = 0;
			int length = 0;
			String selectedText = null;
			IEditorSite iEditorSite = editorPart.getEditorSite();
			if (iEditorSite == null) {
				return;
			}
			//get selection provider
			ISelectionProvider selectionProvider = iEditorSite
					.getSelectionProvider();
			if (selectionProvider == null) {
				return;
			}
			ISelection iSelection = selectionProvider
					.getSelection();
			//offset
			offset = ((ITextSelection) iSelection).getOffset();
			if (iSelection.isEmpty()) {
				return;
			}
			selectedText = ((ITextSelection) iSelection)
					.getText();
			//length
			length = ((ITextSelection) iSelection).getLength();


			open(new URL(String.format("http://www.wolframalpha.com/input/?i=%s", selectedText)));


 
		} catch (Exception e) {	
			//TODO
		}
	}
	public static void open(URL url) {
		// Create the browser
		IWorkbenchBrowserSupport support= PlatformUI.getWorkbench().getBrowserSupport();
		IWebBrowser browser;
		try {
			browser = support.createBrowser(null);
			browser.openURL(url);;
		} catch (PartInitException e) {
			//TODO
			return;
		}
	}
 
	@Override
	public void selectionChanged(IAction action, ISelection selection) {
	}
 
}