package mx.com.nmp.pagos.mimonte.util;

import java.util.LinkedList;
import java.util.Queue;

public class EstadoCuentaLineQueue {

	private Queue<Character> line;


	public EstadoCuentaLineQueue(String line){
		this.line = new LinkedList<Character>();
		if (line  != null) {
			for (char c : line.toCharArray()) {
				this.line.add(c);
			}
		}
	}


	public String get(int length) {
		StringBuffer substring = new StringBuffer();
		if (length > 0) {
			for (int i = 0; i < length; i++) {
				if (this.line.isEmpty()) {
					break;
				}
				substring.append(this.line.poll());
			}
		}
		return substring.toString();
	}

}
