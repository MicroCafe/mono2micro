package requitur;

import requitur.content.Content;

public class ReducedTraceElement {
	private final Content value;
	private final int occurences;

	public ReducedTraceElement(final Content value, final int occurences) {
		super();
		this.value = value;
		this.occurences = occurences;
	}

	public Content getValue() {
		return value;
	}

	public int getOccurences() {
		return occurences;
	}

	@Override
	public String toString() {
		return "(" + value + ")x" + occurences ;
	}
}