package pt.um.mrc.jobs.allmetrics;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

public class IntOrText implements WritableComparable<IntOrText> {

	Boolean isText;
	String text;
	int intw;

	public IntOrText() {
		text = new String();
	}

	public IntOrText(String text, int intw, Boolean isText) {
		this.isText = isText;
		this.intw = intw;
		this.text = text;
	}

	public Boolean getIsText() {
		return isText;
	}

	public void setIsText(Boolean isText) {
		this.isText = isText;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getIntw() {
		return intw;
	}

	public void setIntw(int intw) {
		this.intw = intw;
	}

	@Override
	public String toString() {
		if (isText)
			return text.toString();
		return Integer.toString(intw);
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		isText = in.readBoolean();
		text = in.readUTF();
		intw = in.readInt();
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeBoolean(isText);
		out.writeUTF(text);
		out.writeInt(intw);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + intw;
		result = prime * result + ((isText == null) ? 0 : isText.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IntOrText other = (IntOrText) obj;
		if (intw != other.intw)
			return false;
		if (isText == null) {
			if (other.isText != null)
				return false;
		} else if (!isText.equals(other.isText))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}

	@Override
	public int compareTo(IntOrText o) {

		int cmpIT = this.isText.compareTo(o.getIsText());
		if (cmpIT != 0)
			return cmpIT;
		if (isText) {
			int cmpTxt = text.compareTo(o.getText());
			if (cmpTxt != 0)
				return cmpTxt;
		}
		return new Integer(intw).compareTo(new Integer(o.getIntw()));
	}

}
