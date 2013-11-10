/**
 * 
 */
package tools.textfield;

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
 

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;
 
public class AutoCompleteDocument extends PlainDocument {
 
	/**
	 * The generated serial version UID.
	 */
	private static final long serialVersionUID = -1066544859458614961L;

	private final List<String> dictionary;
 
	private final JTextComponent _textField;
 
	public AutoCompleteDocument(JTextComponent field, ArrayList<String> aDictionary) {
		_textField = field;
		dictionary = aDictionary;
	}
 
	public void addDictionaryEntry(String item) {
		dictionary.add(item.toLowerCase());
	}
 
	@Override
	public void insertString(int offs, String str, AttributeSet a)
			throws BadLocationException {
		super.insertString(offs, str, a);
		String word = autoComplete(getText(0, getLength()));
		if (word != null) {
			super.insertString(offs + str.length(), word, a);
			_textField.setCaretPosition(offs + str.length());
			_textField.moveCaretPosition(getLength());
		}
	}
 
	public String autoComplete(String text) {
		System.out.print("[" );
		for (String string : dictionary) {
			System.out.print(string);
			System.out.print(", ");
		}
		System.out.println("]");
		
		System.out.println("Autocomplete: " + text);
		for (Iterator<String> i = dictionary.iterator(); i.hasNext();) {
			String word = i.next();
			if (word.startsWith(text.toLowerCase())) {
				return word.substring(text.length());
			}
		}
		return null;
	}
 
/**
 * 
 * @param dictionary
 * @return
 */
	public static JTextField createAutoCompleteTextField(ArrayList<String> dictionary) {
		JTextField field = new JTextField(10);
 
		AutoCompleteDocument doc = new AutoCompleteDocument(field, dictionary);
		field.setDocument(doc);
		return field;
	}

	public static void main(String args[]) {
		ArrayList<String> dict = new ArrayList<>(Arrays.asList("Alef++", "alef++", "sourceForge", "SourceFORGE", "JAVA",
				"PROGRAMMATION", "programmation", "Team"));
		JTextField field = AutoCompleteDocument
				.createAutoCompleteTextField(dict);
 
		JFrame frame = new JFrame("Autocomplete");
		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new JLabel("Text Field: "));
		frame.add(field);
		frame.pack();
		frame.setVisible(true);
	}
}
