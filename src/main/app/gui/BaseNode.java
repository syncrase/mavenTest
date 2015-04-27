//package app.gui;
//
//import java.awt.Button;
//import java.awt.Composite;
//import java.awt.GridLayout;
//
//import org.eclipse.ui.part.MultiPageEditorPart;
//
//public class BaseNode extends MultiPageEditorPart {
//
//	/**
//	 * Construction of a graphical user interface based on an xml
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//		//
//		
//        Composite composite = new Composite(getContainer(), SWT.NONE);
//        GridLayout layout = new GridLayout();
//        composite.setLayout(layout);
//        layout.numColumns = 2;
//
//        Button fontButton = new Button(composite, SWT.NONE);
//        GridData gd = new GridData(GridData.BEGINNING);
//        gd.horizontalSpan = 2;
//        fontButton.setLayoutData(gd);
//        fontButton.setText(MessageUtil.getString("ChangeFont")); //$NON-NLS-1$
//
//        fontButton.addSelectionListener(new SelectionAdapter() {
//            public void widgetSelected(SelectionEvent event) {
//                setFont();
//            }
//        });
//
//        int index = addPage(composite);
//        setPageText(index, MessageUtil.getString("Properties")); //$NON-NLS-1$
//        
//	}
//
//}
